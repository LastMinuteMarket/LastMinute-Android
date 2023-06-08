package com.lastminute.repository.api

import com.lastminute.repository.model.placement.NaverDto
import com.lastminute.repository.model.placement.NaverPlacementDto
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PlacementApi {

    @GET("local.json")
    fun getPlacements(
        @Query("query") query: String,
        @Query("display") display: Int
    ): Deferred<Response<NaverDto<NaverPlacementDto>>>
}