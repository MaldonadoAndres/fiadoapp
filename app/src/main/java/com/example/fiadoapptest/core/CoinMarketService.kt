@file:Suppress("BlockingMethodInNonBlockingContext")

package com.example.fiadoapptest.core

import com.example.fiadoapptest.data.model.CryptoCurrency
import com.example.fiadoapptest.data.model.CryptoDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class CoinMarketService {
    private val retrofit = RetrofitHelper.getRetrofit()
    suspend fun getCoins(): List<CryptoCurrency> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(ApiClient::class.java).getCoins()
            response.data.subList(0, 10)
        }
    }

    suspend fun getCoinDetails(symbol: String): CryptoDetails {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(ApiClient::class.java).getCoinDetails(symbol)
            val jsonObject = JSONObject(response.string())
            CryptoDetails.fromJson(jsonObject, symbol)
        }
    }
}