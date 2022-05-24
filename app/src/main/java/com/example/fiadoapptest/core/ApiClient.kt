package com.example.fiadoapptest.core

import com.example.fiadoapptest.data.model.GetCoinsResponse
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiClient {
    @Headers("X-CMC_PRO_API_KEY:b14e15fc-5364-46b4-8e6b-29b8e249cc9c")
    @GET("v1/cryptocurrency/listings/latest")
    suspend fun getCoins(): GetCoinsResponse

    @Headers("X-CMC_PRO_API_KEY:b14e15fc-5364-46b4-8e6b-29b8e249cc9c")
    @GET("/v2/cryptocurrency/info")
    suspend fun getCoinDetails(@Query("symbol") symbol: String): ResponseBody
}