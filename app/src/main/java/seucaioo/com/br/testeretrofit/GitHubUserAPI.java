package seucaioo.com.br.testeretrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubUserAPI {

    @GET("/users/{usuario}")
    Call<Usuario> getUsuario(@Path("usuario") String usuario);

    @GET("/users/{usuario}/followers")
    Call<List<Usuario>> getSeguidores(@Path("usuario") String usuario);

    // Trailing slash is needed
    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
