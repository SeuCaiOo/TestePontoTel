package seucaioo.com.br.testeretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import seucaioo.com.br.testeretrofit.api.Client;
import seucaioo.com.br.testeretrofit.api.Service;
import seucaioo.com.br.testeretrofit.model.Data;
import seucaioo.com.br.testeretrofit.model.DatasResponse;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private DatasAdapter adapter;
    private List<Data> dataList;
    private Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initViews();

    }

    private void initViews() {


        recyclerView = findViewById(R.id.recyclerview);

        dataList = new ArrayList<>();
        adapter = new DatasAdapter(this, dataList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        loadJSON();
    }

    private void loadJSON() {
        try {
            Client client = new Client();
            Service apiService = Client.getClient().create(Service.class);

            Call<DatasResponse> call = apiService.getDatas();
            call.enqueue(new Callback<DatasResponse>() {
                @Override
                public void onResponse(Call<DatasResponse> call, Response<DatasResponse> response) {
                    if (response.isSuccessful()) {
                        List<Data> dataList = response.body().getDatas();
                        if (dataList != null) {
                            for (Data d : dataList) {
                                recyclerView.setAdapter(new DatasAdapter(getApplicationContext(), dataList));
                                Log.d("App", String.format("%s: %s -> %s", d.getName(), d.getPwd(), d.getId()));

                            }
                        } else {
                            Toast.makeText(getBaseContext(), "Falha: " + String.valueOf(response.code()),
                                    Toast.LENGTH_LONG).show();
                        }

                    }

                }

                @Override
                public void onFailure(Call<DatasResponse> call, Throwable t) {
                    Log.d("App", t.getMessage());
                    Toast.makeText(MainActivity.this, "Error Fetching Data!",
                            Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Log.d("App", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }

    }

}
