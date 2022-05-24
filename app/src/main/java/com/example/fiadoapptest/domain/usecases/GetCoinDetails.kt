package com.example.fiadoapptest.domain.usecases

import com.example.fiadoapptest.core.CoinMarketService
import com.example.fiadoapptest.data.model.CryptoDetails

class GetCoinDetails {
    private val service: CoinMarketService = CoinMarketService()
    suspend operator fun invoke(symbol: String): CryptoDetails = service.getCoinDetails(symbol)
}