package emil.meyn.dailytasks;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TaskAPI {
    // this implements for my user not new users... Using OAuth
    // This is how it would look with OAuth
    // @GET("GET /users/{user}.json?auth_token={auth_token}")
    // Call<TaskResponse> getTask(Path@("user") String user, @Path("auth_token") String token);
    // @GET("users/emilmeyn/goals/{name}.json?auth_token=ZBrAv-e7acxACJKLUyBb")
    // Call<TaskResponse> getTask(@Path("name") String name);
    @GET("users/emilmeyn/goals/test.json?auth_token=ZBrAv-e7acxACJKLUyBb")
    Call<TaskResponse> getTask();


}
