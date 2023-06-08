package com.lastminute.ui.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lastminute.repository.NetworkService
import com.lastminute.repository.model.product.ProductAllDto
import com.lastminute.repository.repository.ProductRepository
import com.lastminute.ui.model.ProductDetail
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {

    private val productRepository = ProductRepository

    val productId = MutableLiveData<Long>()

    private val _product = MutableLiveData<ProductDetail>()
    val product: LiveData<ProductDetail>
        get() = _product

    init {
        productId.observeForever {
            getProduct()
        }
    }

    private fun getProduct() {
        if (productId.value == null) return

        viewModelScope.launch {
            val data = productRepository.getProduct(productId.value!!) ?: return@launch

            _product.postValue(
                ProductDetail(
                    productId = data.productId,
                    title = data.placement.title,
                    menu = data.detail.menu,
                    numPeoples = data.detail.reservedPeoples,
                    address = data.placement.roadAddress,
                    lat = data.placement.pointX,
                    lot = data.placement.pointY,
                    writerId = data.writer.userId,
                    writer = data.writer.nickname,
                    time = data.detail.reservedTime,
                    isAllPaid = data.detail.reservationType == "PREPAID",
                    paid = data.detail.pricePaid,
                    price = data.detail.priceNow,
                    content = data.detail.description
                )
            )
        }
    }

}