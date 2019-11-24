package emil.meyn.dailytasks;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    // does it need to be final also?
    // Using the baseURL here means that it is not needed in the response class
    private static final String BASE_URL = "https://www.beeminder.com/api/v1/";

    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    // This creates the retrofit created code for the methods in the API interface
    private static TaskAPI taskAPI = retrofit.create(TaskAPI.class);

    public static TaskAPI getTaskAPI(){
        return taskAPI;
    }

}
