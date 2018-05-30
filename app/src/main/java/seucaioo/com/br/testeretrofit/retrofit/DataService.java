package seucaioo.com.br.testeretrofit.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import seucaioo.com.br.testeretrofit.model.DatasResponse;

public interface DataService {
    @GET("data.json")
    Call<DatasResponse> getDatas();
}
