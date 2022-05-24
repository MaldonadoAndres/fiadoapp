package com.example.fiadoapptest.data.model

import com.google.gson.Gson
import org.json.JSONObject

data class CryptoDetails(
    val name: String,
    val symbol: String,
    val logoUrl: String,
    val description: String
) {
    override fun toString(): String {
        val gson = Gson()
        return gson.toJson(this)
    }

    companion object {
        fun fromJson(jsonObject: JSONObject, symbol: String): CryptoDetails {
            val data = jsonObject.getJSONObject("data").getJSONArray(symbol).getJSONObject(0)
            return CryptoDetails(
                name = data.getString("name"),
                symbol = data.getString("symbol"),
                logoUrl = data.getString("logo"),
                description = data.getString("description")
            )

        }
    }
}
