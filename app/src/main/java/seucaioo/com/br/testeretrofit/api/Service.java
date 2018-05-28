package seucaioo.com.br.testeretrofit.api;

import retrofit2.Call;
import retrofit2.http.GET;
import seucaioo.com.br.testeretrofit.model.DatasResponse;

public interface Service {
    @GET("data.json")
    Call<DatasResponse> getDatas();
}
