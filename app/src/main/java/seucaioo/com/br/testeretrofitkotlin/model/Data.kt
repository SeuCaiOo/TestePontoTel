package seucaioo.com.br.testeretrofitkotlin.model

import com.google.gson.annotations.SerializedName

data class Data (
        @SerializedName("id")
        var id: String,

        @SerializedName("name")
        var name: String,

        @SerializedName("pwd")
        var pwd: Int)