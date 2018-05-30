package seucaioo.com.br.testeretrofit.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import seucaioo.com.br.testeretrofit.model.DataResponse;

public interface DataService {
    @GET("data.json")
    Call<DataResponse> getData();
}
