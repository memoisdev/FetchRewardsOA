package com.onlineassignment.fetchrewards.challenge.data.repository

import com.onlineassignment.fetchrewards.challenge.domain.models.HiringItem

/**
 * Repository acting as the data layer to retrieve a list of items – for now, this repository only
 * handles getting this list from the remote data source.
 */
interface HiringRepository {

    suspend fun fetchHiringList(): Result<List<HiringItem>>

}
