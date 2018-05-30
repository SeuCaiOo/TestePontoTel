package seucaioo.com.br.testeretrofitkotlin.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import seucaioo.com.br.testeretrofitkotlin.model.DataResponse

interface Service {

    @GET("data.json")
    fun getDatas() : Call<DataResponse>
}