package com.onlineassignment.fetchrewards.challenge.domain.usecase

import com.onlineassignment.fetchrewards.challenge.data.repository.HiringRepository
import com.onlineassignment.fetchrewards.challenge.domain.models.HiringItem

/**
 * Use case to retrieve grouped hiring items by listId, and then sorts by name (lexicographically),
 * blank names are filtered out. Might want to consider sorting by id to provide a more intuitive
 * ordering (numerically) – the [HiringItem] will need to be updated to include id from the DTO model.
 */
class GetGroupedHiringItemsUseCase(
    private val fetchRewardsRepository: HiringRepository
) {

    suspend operator fun invoke(): Result<Map<Long, List<HiringItem>>> {
        return fetchRewardsRepository.fetchHiringList().fold(
            onSuccess = { itemsList ->
                val groupedItems = itemsList
                    .filter { it.name.isNotBlank() }
                    .groupBy { it.listId }
                    .toSortedMap()
                    .mapValues { entry ->
                        entry.value.sortedBy { it.name }
                    }

                Result.success(groupedItems)
            },
            onFailure = {
                Result.failure(it)
            }
        )
    }

}
