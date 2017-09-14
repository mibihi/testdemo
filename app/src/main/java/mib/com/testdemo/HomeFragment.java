package mib.com.testdemo;

import android.app.ProgressDialog;
import mib.com.testdemo.R;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class HomeFragment extends android.support.v4.app.Fragment implements DataAdapter.GitListener {
    private static final String TAG = HomeFragment.class.getSimpleName();

    @BindView(R.id.dataList)
    RecyclerView dataList;

    private DataAdapter adapter;
    private Subscription subscription;
    ProgressDialog progressDialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        ButterKnife.bind(this, rootView);

        LinearLayoutManager manll = new LinearLayoutManager(getActivity());
        manll.setOrientation(LinearLayoutManager.VERTICAL);
        dataList.setLayoutManager(manll);

        progressDialog = Methods.setUpProgressDialog(getActivity(), "Please wait...", false);
        getStarredRepos("arriolac");

        return rootView;
    }


    @Override
    public void onDestroy() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        super.onDestroy();
    }

    private void getStarredRepos(String username) {
        subscription = ApiClient.getInstance()
                .getStarredRepos(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<GitHubRepo>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "In onCompleted()");
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.d(TAG, "In onError()");
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onNext(List<GitHubRepo> gitHubRepos) {
                        Log.d(TAG, "In onNext()");
                        adapter = new DataAdapter(getActivity(), gitHubRepos);
                        dataList.setAdapter(adapter);
                        adapter.setDataListener(HomeFragment.this);
                    }
                });
    }

    @Override
    public void onClickItem(GitHubRepo data) {

        Toast.makeText(getActivity(), data.getName(), Toast.LENGTH_SHORT).show();
    }
}
