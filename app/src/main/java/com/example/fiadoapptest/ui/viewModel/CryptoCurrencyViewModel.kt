package com.example.fiadoapptest.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fiadoapptest.data.model.CryptoCurrency
import com.example.fiadoapptest.data.model.CryptoDetails
import com.example.fiadoapptest.domain.usecases.GetCoinDetails
import com.example.fiadoapptest.domain.usecases.GetCoins
import kotlinx.coroutines.launch

class CryptoCurrencyViewModel() : ViewModel() {

    val listOfCoins = MutableLiveData<List<CryptoCurrency>?>()
    val isLoading = MutableLiveData<Boolean>()
    val isLoadingDetails = MutableLiveData<Boolean>()
    val cryptoDetails = MutableLiveData<CryptoDetails>()

    private val getCoinsUseCase = GetCoins()
    private val getCoinDetails = GetCoinDetails()

    fun getCoins() {

        isLoading.postValue(true)
        viewModelScope.launch {
            listOfCoins.postValue(getCoinsUseCase())
            isLoading.postValue(false)
        }
    }

    fun loadDetails(symbol: String) {
        isLoadingDetails.postValue(true)
        viewModelScope.launch {
            val details = getCoinDetails(symbol)
            cryptoDetails.postValue(details)
            isLoadingDetails.postValue(false)
        }
    }
}