package com.onlineassignment.fetchrewards.challenge.data.remote.fetchrewards.models

import com.onlineassignment.fetchrewards.challenge.domain.models.HiringItem

/**
 * Data transfer object to parse response from [FetchHiringService]
 */
data class HiringItemDto(
    val id: Long,
    val listId: Long,
    val name: String?
)

/**
 * Converts to domain model; for now, it omits id.
 */
fun HiringItemDto.toDomainModel(): HiringItem {
    return HiringItem(
        listId = this.listId,
        name = this.name ?: ""
    )
}
