package mib.com.testdemo;
import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
/**
 * Created by mibihi on 9/12/17.
 */

public interface GitHubService {
    @GET("users/{user}/starred")
    Observable<List<GitHubRepo>> getStarredRepositories(@Path("user") String userName);

}
