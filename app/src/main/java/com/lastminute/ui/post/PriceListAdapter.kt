package com.lastminute.ui.post

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lastminute.model.internal.PriceTerm
import com.lastminute.ui.databinding.ItemPriceListBinding

class PriceListAdapter() : RecyclerView.Adapter<PriceListAdapter.PriceTermHolder>() {
    private val dataList = mutableListOf<PriceTerm>()

    class PriceTermHolder(private val binding: ItemPriceListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: PriceTerm) {
            binding.data = data
            binding.clPriceSelect.originPrice = data.originPrice
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PriceTermHolder {
        val binding = ItemPriceListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PriceTermHolder(binding)
    }

    override fun onBindViewHolder(holder: PriceTermHolder, position: Int) {
        holder.onBind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

    fun putData(dataList: List<PriceTerm>) {
        this.dataList.addAll(dataList)
        notifyDataSetChanged()
    }

    fun putData(data: PriceTerm) {
        this.dataList.add(data)
        notifyDataSetChanged()
    }
}