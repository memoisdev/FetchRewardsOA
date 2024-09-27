package com.onlineassignment.fetchrewards.challenge.data.remote.fetchrewards.api

import com.onlineassignment.fetchrewards.challenge.data.remote.fetchrewards.models.HiringItemDto
import retrofit2.http.GET

/**
 * Interface for [FetchHiringService]; contains an API to retrieve a list of items.
 */
interface FetchHiringService {

    /**
     * Retrieves a list of [HiringItemDto] from the API
     */
    @GET("hiring.json")
    suspend fun getHiringList(): List<HiringItemDto>

}
