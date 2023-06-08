package com.lastminute.repository.api

import com.lastminute.repository.model.CommonResponse
import com.lastminute.repository.model.Page
import com.lastminute.repository.model.ProductSummaryDto
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    @GET("api/search")
    fun searchProducts(
        @Query("lat") lat: Double,
        @Query("lot") lot: Double
    ): Deferred<Response<CommonResponse<Page<ProductSummaryDto>>>>
}