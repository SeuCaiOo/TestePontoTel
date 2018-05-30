package seucaioo.com.br.testeretrofit.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInitializer {

    public static final String BASE_URL =
            "https://s3-sa-east-1.amazonaws.com/pontotel-docs/";


    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public DataService dataService() {
        return retrofit.create(DataService.class);
    }

}
