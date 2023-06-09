package com.lastminute.ui.post

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lastminute.repository.model.product.PlacementDto
import com.lastminute.repository.model.product.ProductAllDto
import com.lastminute.repository.model.product.ProductCreateDto
import com.lastminute.repository.repository.NaverRepository
import com.lastminute.repository.repository.ProductRepository
import com.lastminute.ui.model.Placement
import com.lastminute.util.GeoPoint
import com.lastminute.util.GeoTrans
import kotlinx.coroutines.launch
import kotlin.streams.toList

class PostViewModel : ViewModel() {

    val productId = MutableLiveData<Long>()
    val placement = MutableLiveData<Placement>()

    private val naverRepository = NaverRepository
    private val productRepository = ProductRepository
    val result = MutableLiveData<List<Placement>>()

    fun getPlacements(query: String) {
        viewModelScope.launch {
            result.postValue(
                naverRepository.getPlacements(query, 3)?.stream()
                    ?.map {
                        val geo = GeoTrans.convert(GeoTrans.KATEC, GeoTrans.GEO, GeoPoint(it.mapx, it.mapy))
                        val title = it.title.replace("<b>", "").replace("</b>", "")

                        Placement(
                            title = title,
                            roadAddress = it.roadAddress,
                            lat = geo.x,
                            lot = geo.y
                        )
                    }?.toList()
            )
        }
    }

    fun postProduct(productCreate: ProductCreateDto) {
        viewModelScope.launch {
            val product = productRepository.createProduct(productCreate)
            productId.postValue(product?.productId)
        }
    }
}