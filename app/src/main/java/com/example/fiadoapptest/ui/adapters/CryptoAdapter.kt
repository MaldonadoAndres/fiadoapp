package com.example.fiadoapptest.ui.adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableRow
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fiadoapptest.R
import com.example.fiadoapptest.data.model.CryptoCurrency
import com.example.fiadoapptest.ui.view.CryptoDetailsActivity


private const val TAG = "CryptoAdapter"

class CryptoAdapter(private var cryptos: List<CryptoCurrency>) :
    RecyclerView.Adapter<CryptoAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.crypto_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cryptos[position])
    }

    override fun getItemCount(): Int {
        return cryptos.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setCryptos(newCryptos: List<CryptoCurrency>) {
        cryptos = newCryptos
        notifyDataSetChanged()
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvCmcRank: TextView = itemView.findViewById(R.id.tvCmcRank)
        private val tvSymbol: TextView = itemView.findViewById(R.id.tvSymbol)
        private val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
        private val tvChange: TextView = itemView.findViewById(R.id.tvChange)
        private val row: TableRow = itemView.findViewById(R.id.row)

        @SuppressLint("SetTextI18n")
        fun bind(crypto: CryptoCurrency) {
            tvCmcRank.text = crypto.cmcRank.toString()
            tvSymbol.text = crypto.symbol
            tvPrice.text = "$%.2f".format(crypto.quote.usd.price)
            tvChange.text = "${"%.2f".format(crypto.quote.usd.percentChange24H)}%"
            row.setOnClickListener { row ->
                val intent = Intent(row.context, CryptoDetailsActivity::class.java)
                intent.putExtra("SYMBOL", crypto.symbol)
                row.context.startActivity(intent)
            }
        }


    }

}