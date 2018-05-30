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
import seucaioo.com.br.testeretrofitkotlin.model.DatasResponse

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

    private fun loadJSON() = try {

        val retrofit = RetrofitInitializer()
        val service = retrofit.dataService()

        val call : Call<DatasResponse> = service.getDatas()
        call.enqueue( object : Callback<DatasResponse?> {
            override fun onResponse(call: Call<DatasResponse?>?, response: Response<DatasResponse?>?) {
                response?.let {
                    swipeRefreshLayout.isRefreshing = false
                    response?.body()?.datas.let {
                        val dataList : List<Data>? = it
                        if (dataList != null) {
                            for (d in dataList) {
                                recyclerView.adapter = DataAdapter(applicationContext, dataList)
                                Log.d("App", String.format("%s: %s -> %s", d.name, d.pwd, d.id))
                            }
                        } else {
                            Toast.makeText(baseContext,
                                    "Falha: " + response.code().toString(),
                                    Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }

            override fun onFailure(call: Call<DatasResponse?>?, t: Throwable?) {
                swipeRefreshLayout.isRefreshing = false
                recyclerView.adapter = DataAdapter(applicationContext, dataList)
                Log.d("App", t?.message)
                Toast.makeText(this@MainActivity, "Erro ao buscar Dados!",
                        Toast.LENGTH_SHORT).show()
            }
        }
        )

/*
        call.enqueue(object : Callback<DatasResponse> {
            override fun onResponse(call: Call<DatasResponse>, response: Response<DatasResponse>) {
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

            override fun onFailure(call: Call<DatasResponse>, t: Throwable) {
                swipeRefreshLayout.isRefreshing = false
                val dataList = listOf<Data>()
                recyclerView.adapter = DataAdapter(applicationContext, dataList)
                Log.d("App", t.message)
                Toast.makeText(this@MainActivity, "Erro ao buscar Dados!",
                        Toast.LENGTH_SHORT).show()
            }
        })

*/

    } catch (e: Exception) {
        Log.d("App", e.message)
        Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
    }



}
