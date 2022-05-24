package com.example.fiadoapptest.core

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitHelper {

    fun getRetrofit():Retrofit{
        val httpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        httpClientBuilder.addInterceptor(loggingInterceptor)
        httpClientBuilder.connectTimeout(30, TimeUnit.SECONDS)
        httpClientBuilder.readTimeout(30, TimeUnit.SECONDS)
        httpClientBuilder.writeTimeout(30, TimeUnit.SECONDS)
        val client: OkHttpClient = httpClientBuilder.build()
        return Retrofit.Builder()
            .baseUrl("https://pro-api.coinmarketcap.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
}