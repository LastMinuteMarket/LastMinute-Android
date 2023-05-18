package com.lastminute.ui.post

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.ObservableField
import com.lastminute.ui.databinding.ViewPriceSelectBinding

class PriceSelectLayout(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {
    private val binding = ViewPriceSelectBinding.inflate(LayoutInflater.from(context), this, true)

    var originPrice = 10000
        set(value) {
            field = if (value == 0) 1 else value
        }

    private val priceData = PriceField()

    init {
        binding.priceData = priceData
        binding.etPrice.setOnFocusChangeListener { view, b ->
            if (binding.etPrice.text.isNullOrEmpty())
                binding.etPrice.setText("0")

            val price = priceData.price.get()?.toInt() ?: 0
            val newPercent = price * 100 / originPrice
            priceData.percent.set(newPercent.toString())
        }

        binding.etPercent.setOnFocusChangeListener { view, b ->
            if (binding.etPercent.text.isNullOrEmpty())
                binding.etPercent.setText("0")

            val percent = priceData.percent.get()?.toInt() ?: 0
            val newPrice = originPrice * percent / 100
            priceData.price.set(newPrice.toString())
        }
    }

    inner class PriceField() {
        val percent = ObservableField<String>("0")
        val price = ObservableField<String>("0")
    }

}