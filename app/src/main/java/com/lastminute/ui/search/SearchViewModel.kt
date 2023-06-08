package com.lastminute.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lastminute.repository.NetworkService
import com.lastminute.repository.repository.SearchRepository
import com.lastminute.ui.model.Point
import com.lastminute.ui.model.ProductSummary
import kotlinx.coroutines.launch
import kotlin.streams.toList

class SearchViewModel : ViewModel() {

    private val searchRepository = SearchRepository(NetworkService.searchApi)

    private val _products = MutableLiveData<MutableList<ProductSummary>>()
    val products: LiveData<MutableList<ProductSummary>>
        get() = _products

    val point = MutableLiveData<Point>()

    init {
        point.observeForever {
            getProducts()
        }
    }

    private fun getProducts() {
        viewModelScope.launch {

            val data = searchRepository.searchProducts(point.value?.lat ?: 0.0, point.value?.lot ?: 0.0)
                ?.stream()?.map {
                    ProductSummary(
                        id = it.productId,
                        lot = it.placement.pointX,
                        lat = it.placement.pointY,
                        title = it.placement.title,
                        menu = "${it.detail.menu} (${it.detail.reservedPeoples}인)",
                        time = it.detail.reservedTime,
                        paid = "${it.detail.pricePaid}원",
                        price = "${it.detail.priceNow}원",
                        likes = "0",
                        type = if (it.detail.reservationType == "PREPAID") ProductSummary.ProductType.PREPAID else ProductSummary.ProductType.DEPOSIT
                    )
                }?.toList()

            if (data != null)
                _products.postValue(data!! as MutableList<ProductSummary>?)
        }
    }
}