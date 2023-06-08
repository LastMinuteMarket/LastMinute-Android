package com.lastminute.repository.repository

import android.net.Network
import com.lastminute.repository.NetworkService
import com.lastminute.repository.api.ProductApi
import com.lastminute.repository.model.product.ProductAllDto
import com.lastminute.repository.model.product.ProductCreateDto
import com.lastminute.repository.model.product.ProductSummaryDto

object ProductRepository : BaseRepository() {

    private val apiInterface = NetworkService.productApi

    suspend fun searchProducts(lat: Double, lot: Double): MutableList<ProductSummaryDto>? {
        return safeApiCall(
            call = {apiInterface.searchProducts(lat, lot).await()},
            error = "Search fail"
        )?.data?.content?.toMutableList()
    }

    suspend fun getProduct(productId: Long): ProductAllDto? {
        return safeApiCall(
            call = {apiInterface.getProduct(productId).await()},
            error = "Get fail"
        )?.data
    }

    suspend fun createProduct(product: ProductCreateDto): ProductAllDto? {
        return safeApiCall(
            call = {apiInterface.postProduct(product).await()},
            error = "Get fail"
        )?.data
    }
}