package com.example.fiadoapptest.domain.usecases

import com.example.fiadoapptest.core.CoinMarketService
import com.example.fiadoapptest.data.model.CryptoCurrency

class GetCoins {
    private val service: CoinMarketService = CoinMarketService()
    suspend operator fun invoke(): List<CryptoCurrency> = service.getCoins()

}