package com.lastminute.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lastminute.ui.model.ProductSummary
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {

    private val _products = MutableLiveData<List<ProductSummary>>()
    val products: LiveData<List<ProductSummary>>
        get() = _products

    fun getProducts() {
        viewModelScope.launch {
            kotlin.runCatching {

            }
        }
    }
}