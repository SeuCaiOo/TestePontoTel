package seucaioo.com.br.testeretrofitkotlin.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class Client() {


    private val retrofit = Retrofit.Builder()
            .baseUrl("https://s3-sa-east-1.amazonaws.com/pontotel-docs/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun dataService() = retrofit.create(Service::class.java)
}