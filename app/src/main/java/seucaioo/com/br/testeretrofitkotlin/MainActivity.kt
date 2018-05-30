package seucaioo.com.br.testeretrofitkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import seucaioo.com.br.testeretrofitkotlin.model.Data
import seucaioo.com.br.testeretrofitkotlin.DataAdapter
import seucaioo.com.br.testeretrofitkotlin.api.Client
import seucaioo.com.br.testeretrofitkotlin.api.Service
import seucaioo.com.br.testeretrofitkotlin.model.DataResponse
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

//    private var adapter: DatasAdapter? = null
//    private var dataList: List<Data>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        init()
    }

    private fun init() {

        val dataList = listOf<Data>()
        val adapter = DataAdapter(this, dataList)

        val recyclerView = recyclerView
        val mLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = adapter



        val swipeRefreshLayout = swipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener(OnRefreshLayout())

    }

    private fun OnRefreshLayout(): SwipeRefreshLayout.OnRefreshListener {

        return SwipeRefreshLayout.OnRefreshListener { loadJSON() }
    }

    private fun loadJSON() {
        try {
            val client: Client
            val apiService: Service = Client().dataService()

            val call : Call<DataResponse> = apiService.getDatas()
            call.enqueue(object : Callback<DataResponse> {
                override fun onResponse(call: Call<DataResponse>, response: Response<DataResponse>) {
                    if (response.isSuccessful()) {
                        swipeRefreshLayout.isRefreshing = false
                        val dataList = response.body()!!.datas
                        if (dataList != null) {
                            for (d in dataList!!) {
                                recyclerView.adapter = DataAdapter(applicationContext, dataList)
                                Log.d("App", String.format(
                                        "%s: %s -> %s", d.name, d.pwd, d.id))
                            }
                        } else {
                            Toast.makeText(baseContext,
                                    "Falha: " + response.code().toString(),
                                    Toast.LENGTH_LONG).show()
                        }

                    }

                }

                override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                    swipeRefreshLayout.isRefreshing = false
//                    recyclerView.adapter = DataAdapter(applicationContext, dataList)
                    Log.d("App", t.message)
                    Toast.makeText(this@MainActivity, "Erro ao buscar Dados!",
                            Toast.LENGTH_SHORT).show()
                }
            })


        } catch (e: Exception) {
            Log.d("App", e.message)
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
        }
    }



}
