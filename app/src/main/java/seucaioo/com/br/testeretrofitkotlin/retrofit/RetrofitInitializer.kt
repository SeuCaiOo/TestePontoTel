package seucaioo.com.br.testeretrofitkotlin.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class RetrofitInitializer() {

    private val retrofit = Retrofit.Builder()
            .baseUrl("https://s3-sa-east-1.amazonaws.com/pontotel-docs/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun dataService() = retrofit.create(DataService::class.java)
}