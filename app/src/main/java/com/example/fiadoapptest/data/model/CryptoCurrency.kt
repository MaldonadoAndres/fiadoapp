package com.example.fiadoapptest.data.model

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName


data class GetCoinsResponse(val data: List<CryptoCurrency>) {
    override fun toString(): String {
        val gson = Gson()
        return gson.toJson(this)
    }
}


data class CryptoCurrency(
    @SerializedName("cmc_rank") val cmcRank: Int,
    val symbol: String,
    val quote: Quote
) {

    override fun toString(): String {
        val gson = Gson()
        return gson.toJson(this)
    }

    data class Quote(@SerializedName("USD") val usd: USD) {
        override fun toString(): String {
            val gson = Gson()
            return gson.toJson(this)
        }
    }

    data class USD(
        val price: Double,
        @SerializedName("percent_change_24h") val percentChange24H: Double
    ) {
        override fun toString(): String {
            val gson = Gson()
            return gson.toJson(this)
        }
    }


}





