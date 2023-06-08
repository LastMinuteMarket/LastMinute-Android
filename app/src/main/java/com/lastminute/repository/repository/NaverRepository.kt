package com.lastminute.repository.repository

import com.lastminute.repository.NaverService
import com.lastminute.repository.model.placement.NaverPlacementDto

object NaverRepository : BaseRepository() {
    private val apiInterface = NaverService.placementApi

    suspend fun getPlacements(query: String, display: Int): MutableList<NaverPlacementDto>? {
        return safeApiCall(
            call = { apiInterface.getPlacements(query, display).await()},
            error = "Get fail"
        )?.items?.toMutableList()
    }
}