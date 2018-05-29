package seucaioo.com.br.testeretrofit;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
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

    private DatasAdapter adapter;
    private List<Data> dataList;


    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {

        dataList = new ArrayList<>();
        adapter = new DatasAdapter(this, dataList);

        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(OnRefreshListener());

        loadJSON();

    }

    private SwipeRefreshLayout.OnRefreshListener OnRefreshListener() {
        return new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadJSON();
            }
        };
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
                        swipeRefreshLayout.setRefreshing(false);
                        List<Data> dataList = response.body().getDatas();
                        if (dataList != null) {
                            for (Data d : dataList) {
                                recyclerView.setAdapter(
                                        new DatasAdapter(getApplicationContext(), dataList));
                                Log.d("App", String.format(
                                        "%s: %s -> %s", d.getName(), d.getPwd(), d.getId()));
                            }
                        } else {
                            Toast.makeText(getBaseContext(),
                                    "Falha: " + String.valueOf(response.code()),
                                    Toast.LENGTH_LONG).show();
                        }

                    }

                }

                @Override
                public void onFailure(Call<DatasResponse> call, Throwable t) {
                    swipeRefreshLayout.setRefreshing(false);
                    recyclerView.setAdapter(new DatasAdapter(getApplicationContext(), dataList));
                    Log.d("App", t.getMessage());
                    Toast.makeText(MainActivity.this, "Erro ao buscar Dados!",
                            Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Log.d("App", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }

    }

}
