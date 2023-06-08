package com.lastminute.ui.post

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lastminute.ui.databinding.ItemPlacementListBinding
import com.lastminute.ui.model.Placement

class PlacementListAdapter(private val onClick: (placement: Placement) -> Unit) : RecyclerView.Adapter<PlacementListAdapter.PlacementHolder>() {
    private val dataList = mutableListOf<Placement>()

    class PlacementHolder(private val binding: ItemPlacementListBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: Placement) {
            binding.data = data
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlacementHolder {
        val binding = ItemPlacementListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlacementHolder(binding)
    }

    override fun onBindViewHolder(holder: PlacementHolder, position: Int) {
        holder.onBind(dataList[position])

        holder.itemView.setOnClickListener {
            this.onClick(dataList[position])
        }
    }

    override fun getItemCount(): Int = dataList.size

    fun setData(dataList: List<Placement>) {
        this.dataList.clear()
        this.dataList.addAll(dataList)
        notifyDataSetChanged()
    }
}