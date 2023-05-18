package com.lastminute.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lastminute.model.internal.ProductSummary
import com.lastminute.ui.databinding.ItemPlaceListBinding

class ProductListAdapter(private val onClick: () -> Unit) : RecyclerView.Adapter<ProductListAdapter.ProductHolder>() {
    private val dataList = mutableListOf<ProductSummary>()

    class ProductHolder(private val binding: ItemPlaceListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: ProductSummary) {
            binding.data = data

            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val binding = ItemPlaceListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ProductHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        holder.onBind(dataList[position])

        holder.itemView.setOnClickListener {
            this.onClick()
        }
    }

    override fun getItemCount(): Int = dataList.size

    fun putData(dataList: List<ProductSummary>) {
        this.dataList.addAll(dataList)
        notifyDataSetChanged()
    }

    fun clearData() {
        this.dataList.clear()
    }


}