package seucaioo.com.br.testeretrofitkotlin.retrofit

import retrofit2.Call
import retrofit2.http.GET
import seucaioo.com.br.testeretrofitkotlin.model.DataResponse

interface DataService {

    @GET("data.json")
    fun getData() : Call<DataResponse>
}