package seucaioo.com.br.testeretrofitkotlin.model

import com.google.gson.annotations.SerializedName

class DataResponse(
        @SerializedName("data")
        var data: List<Data>)