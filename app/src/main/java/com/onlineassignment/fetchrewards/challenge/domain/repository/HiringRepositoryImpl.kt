package com.onlineassignment.fetchrewards.challenge.domain.repository

import android.util.Log
import com.onlineassignment.fetchrewards.challenge.data.remote.fetchrewards.api.FetchHiringService
import com.onlineassignment.fetchrewards.challenge.data.remote.fetchrewards.models.toDomainModel
import com.onlineassignment.fetchrewards.challenge.data.repository.HiringRepository
import com.onlineassignment.fetchrewards.challenge.domain.models.HiringItem

class HiringRepositoryImpl(
    private val fetchRewardsService: FetchHiringService
) : HiringRepository {

    override suspend fun fetchHiringList(): Result<List<HiringItem>> {
        return try {
            val response = fetchRewardsService.getHiringList()
            if (response.isEmpty()) {
                Log.w(HiringRepository::class.java.simpleName, "Empty list received")
            }
            Result.success(response.map { it.toDomainModel() })
        } catch (ex: Exception) {
            // Generic/catch all for now...
            Log.e(HiringRepository::class.java.simpleName, "Error fetching hiring list", ex)
            Result.failure(ex)
        }
    }

}
