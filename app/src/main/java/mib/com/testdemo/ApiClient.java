package mib.com.testdemo;
import android.support.annotation.NonNull;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
/**
 * Created by mibihi on 9/12/17.
 */

public class ApiClient {
    public static final String BASE_URL = "https://api.github.com/";

    private static Retrofit retrofit = null;
    private GitHubService gitHubService;
    private static ApiClient instance;

    private ApiClient() {


        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }

        gitHubService = retrofit.create(GitHubService.class);

    }

    public static ApiClient getInstance() {
        if (instance == null) {
            instance = new ApiClient();
        }
        return instance;
    }

    public Observable<List<GitHubRepo>> getStarredRepos(@NonNull String userName) {
        return gitHubService.getStarredRepositories(userName);
    }

}
