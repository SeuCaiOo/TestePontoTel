package seucaioo.com.br.testeretrofit;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button btnUsuario, btnSeguidores;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnUsuario = findViewById(R.id.btnUsuario);

        btnUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GitHubUserAPI githubUser = GitHubUserAPI.retrofit.create(GitHubUserAPI.class);
                final Call<Usuario> call = githubUser.getUsuario("SeuCaiOo");
                dialog = new ProgressDialog(MainActivity.this);
                dialog.setMessage("Aguarde");
                dialog.setCancelable(false);
                dialog.show();
                call.enqueue(new Callback<Usuario>() {
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                        if (dialog.isShowing())
                            dialog.dismiss();
                        int code = response.code();
                        if (code == 200) {
                            Usuario usuario = response.body();
                            Toast.makeText(getBaseContext(), "Nome do usuário: " +
                                    usuario.name, Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getBaseContext(), "Falha: " + String.valueOf(code),
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {
                        if (dialog.isShowing())
                            dialog.dismiss();
                        Toast.makeText(getBaseContext(), t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
        });


        btnSeguidores = findViewById(R.id.btnSeguidores);

        btnSeguidores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GitHubUserAPI githubSeguidores = GitHubUserAPI.retrofit.create(GitHubUserAPI.class);
                final Call<List<Usuario>> call = githubSeguidores.getSeguidores("SeuCaiOo");
                dialog = new ProgressDialog(MainActivity.this);
                dialog.setMessage("Aguarde");
                dialog.setCancelable(false);
                dialog.show();
                call.enqueue(new Callback<List<Usuario>>() {
                    @Override
                    public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                        if (dialog.isShowing())
                            dialog.dismiss();
                        int code = response.code();
                        List<Usuario> lista = response.body();
                        if (code == 200) {
                            Toast.makeText(getBaseContext(), "OK: " + String.valueOf(code),
                                    Toast.LENGTH_LONG).show();
                            for (Usuario usuario: lista) {
                                Log.d("MainActivity", " -> Seguidores: " + usuario.login);
                            }
                        } else {
                            Toast.makeText(getBaseContext(), "Falha: " + String.valueOf(code),
                                    Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<List<Usuario>> call, Throwable t) {
                        if (dialog.isShowing())
                            dialog.dismiss();
                    }
                });
            }
        });

    }


}
