package com.example.fiadoapptest.ui.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.fiadoapptest.databinding.ActivityCryptoDetailsBinding
import com.example.fiadoapptest.ui.viewModel.CryptoCurrencyViewModel

class CryptoDetailsActivity : AppCompatActivity() {
    private val cryptoCurrencyViewModel: CryptoCurrencyViewModel by viewModels()
    private lateinit var binding: ActivityCryptoDetailsBinding

    override fun onResume() {
        val symbol = intent.getStringExtra("SYMBOL")
        if (symbol != null) {
            cryptoCurrencyViewModel.loadDetails(symbol)
        }
        super.onResume()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCryptoDetailsBinding.inflate(layoutInflater)
        cryptoCurrencyViewModel.cryptoDetails.observe(this) { details ->
            Glide.with(this).load(details.logoUrl).into(binding.ivLogo)
            binding.tvName.text = details.name
            binding.tvSymbol.text = details.symbol
            binding.tvDescription.text = details.description
        }
        cryptoCurrencyViewModel.isLoadingDetails.observe(this) { isLoadingDetails ->
            binding.progressIndicator.visibility = if (isLoadingDetails) View.VISIBLE else View.GONE

        }
        setContentView(binding.root)
    }
}