package com.lastminute.repository.api

import com.lastminute.repository.model.CommonResponse
import com.lastminute.repository.model.Page
import com.lastminute.repository.model.product.ProductAllDto
import com.lastminute.repository.model.product.ProductCreateDto
import com.lastminute.repository.model.product.ProductSummaryDto
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

interface ProductApi {

    @GET("api/search")
    fun searchProducts(
        @Query("lat") lat: Double,
        @Query("lot") lot: Double
    ): Deferred<Response<CommonResponse<Page<ProductSummaryDto>>>>

    @GET("api/products/{productId}")
    fun getProduct(
        @Path("productId") productId: Long
    ): Deferred<Response<CommonResponse<ProductAllDto>>>

    @POST("api/products")
    fun postProduct(
        @Body product: ProductCreateDto
    ): Deferred<Response<CommonResponse<ProductAllDto>>>
}