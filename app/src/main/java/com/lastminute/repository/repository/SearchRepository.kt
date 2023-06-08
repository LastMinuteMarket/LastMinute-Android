package com.lastminute.repository.repository

import com.lastminute.repository.api.SearchApi
import com.lastminute.repository.model.ProductSummaryDto

class SearchRepository(private val apiInterface: SearchApi) : BaseRepository() {

    suspend fun searchProducts(lat: Double, lot: Double) : MutableList<ProductSummaryDto>? {
        return safeApiCall(
            call = {apiInterface.searchProducts(lat, lot).await()},
            error = "Search fail"
        )?.data?.content?.toMutableList()
    }
}