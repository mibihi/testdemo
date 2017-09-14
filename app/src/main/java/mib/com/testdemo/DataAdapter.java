package mib.com.testdemo;

import android.content.Context;
import mib.com.testdemo.R;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by mibihi on 9/11/17.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.OrderItemViewHolder> implements View.OnClickListener{
    Context context;
    private List<GitHubRepo> gitHubRepos;
    GitListener cityListener;

    public DataAdapter(Context paramContext, List<GitHubRepo> gitHubRepos) {
        this.context = paramContext;
        this.gitHubRepos = gitHubRepos;
    }

    @Override
    public int getItemCount() {
        return this.gitHubRepos.size();
    }

    @Override
    public void onBindViewHolder(OrderItemViewHolder paramOrderViewHolder, int paramInt) {

        GitHubRepo gitHubRepo = gitHubRepos.get(paramInt);

        paramOrderViewHolder.textRepoName.setText(gitHubRepo.name);
        paramOrderViewHolder.textRepoDescription.setText(gitHubRepo.description);
        paramOrderViewHolder.textLanguage.setText("Language: " + gitHubRepo.language);
        paramOrderViewHolder.textStars.setText("Stars: " + gitHubRepo.stargazersCount);

        paramOrderViewHolder.itemRL.setTag(gitHubRepo);
        paramOrderViewHolder.itemRL.setOnClickListener(this);

    }

    @Override
    public OrderItemViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int viewType) {

        return new OrderItemViewHolder(LayoutInflater.from(paramViewGroup.getContext()).inflate(R.layout.item_github_repo, paramViewGroup, false));

    }


    @Override
    public void onClick(View v) {
        Object tag = v.getTag();

        switch (v.getId()) {
            case R.id.itemRL:
                if (tag != null && tag instanceof GitHubRepo && this.cityListener != null) {

                    GitHubRepo data = (GitHubRepo) tag;
                    this.cityListener.onClickItem(data);
                }

                break;

        }
    }

    public void setDataListener(GitListener cityListener) {
        this.cityListener = cityListener;
    }

    public static class OrderItemViewHolder extends RecyclerView.ViewHolder {

        protected View v;
        @BindView(R.id.text_repo_name)
        protected TextView textRepoName;
        @BindView(R.id.text_repo_description)
        protected TextView textRepoDescription;
        @BindView(R.id.text_language)
        protected TextView textLanguage;
        @BindView(R.id.text_stars)
        protected TextView textStars;
        @BindView(R.id.itemRL)
        protected RelativeLayout itemRL;


        public OrderItemViewHolder(View view) {
            super(view);
            this.v = view;
            ButterKnife.bind(this, view);

        }

    }


    public interface GitListener {

        void onClickItem(GitHubRepo city);
    }

}
