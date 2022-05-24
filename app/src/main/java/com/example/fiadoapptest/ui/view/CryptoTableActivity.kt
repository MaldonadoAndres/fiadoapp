package com.example.fiadoapptest.ui.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fiadoapptest.databinding.ActivityCryptoTableBinding
import com.example.fiadoapptest.ui.adapters.CryptoAdapter
import com.example.fiadoapptest.ui.viewModel.CryptoCurrencyViewModel

class CryptoTableActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCryptoTableBinding
    private val cryptoCurrencyViewModel: CryptoCurrencyViewModel by viewModels()
    override fun onResume() {
        super.onResume()
        cryptoCurrencyViewModel.getCoins()
        binding.tvLocation.text = intent.getStringExtra("LOCATION")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCryptoTableBinding.inflate(layoutInflater)
        val progressIndicator = binding.progressIndicator
        setUpRecyclerView()
        cryptoCurrencyViewModel.isLoading.observe(this) { isLoading ->
            progressIndicator.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        setContentView(binding.root)
    }


    private fun setUpRecyclerView() {
        val rv = binding.recyclerView
        rv.layoutManager = LinearLayoutManager(this)
        rv.setHasFixedSize(true)
        val adapter = CryptoAdapter(emptyList())
        rv.adapter = adapter
        cryptoCurrencyViewModel.listOfCoins.observe(this) { coins ->
            if (coins != null) {
                adapter.setCryptos(coins)
            }
        }
    }
}