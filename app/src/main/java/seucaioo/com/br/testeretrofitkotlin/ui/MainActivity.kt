package seucaioo.com.br.testeretrofitkotlin.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import seucaioo.com.br.testeretrofitkotlin.R
import seucaioo.com.br.testeretrofitkotlin.model.Data
import seucaioo.com.br.testeretrofitkotlin.retrofit.RetrofitInitializer
import seucaioo.com.br.testeretrofitkotlin.model.DataResponse

class MainActivity : AppCompatActivity() {

    val dataList = listOf<Data>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {

        val recyclerView = recyclerView
        val mLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = DataAdapter(this,dataList)

        val swipeRefreshLayout = swipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener(OnRefreshLayout())

        loadJSON()
    }

    private fun OnRefreshLayout(): SwipeRefreshLayout.OnRefreshListener {

        return SwipeRefreshLayout.OnRefreshListener { loadJSON() }
    }

    private fun loadJSON() {
        try {
            val retrofit = RetrofitInitializer()
            val service = retrofit.dataService()
            val call : Call<DataResponse> = service.getData()
            call.enqueue(object : Callback<DataResponse?> {
                override fun onResponse(call: Call<DataResponse?>?, response: Response<DataResponse?>?) {
                    response?.body()?.let {
                        if (response.isSuccessful) {
                            swipeRefreshLayout.isRefreshing = false
                            it.data.let {
                                for (d in it) {
                                    Log.d("App", String.format(
                                            "ID: %s -> Name: %s -> Pwd: %s", d.id, d.name, d.pwd))
                                    recyclerView.adapter = DataAdapter(applicationContext, it)
                                }
                            }
                        } else {
                            Log.d("App", "Erro: " + response.code().toString())
                        }
                    }
                }

                override fun onFailure(call: Call<DataResponse?>?, t: Throwable?) {
                    swipeRefreshLayout.isRefreshing = false
                    recyclerView.adapter = DataAdapter(applicationContext, dataList)
                    Log.d("App", t?.message)
                    Toast.makeText(this@MainActivity,
                            "Erro ao buscar dados.\nVerifique sua conexão com a Internet !",
                            Toast.LENGTH_LONG).show()
                }
            })
        } catch (e: Exception) {
            Log.d("App", e.message)
            Toast.makeText(this, "Erro", Toast.LENGTH_LONG).show()
        }
    }

}
