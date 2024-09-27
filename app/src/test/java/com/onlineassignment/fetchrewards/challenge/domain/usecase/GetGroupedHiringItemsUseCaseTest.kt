package com.onlineassignment.fetchrewards.challenge.domain.usecase

import com.onlineassignment.fetchrewards.challenge.data.repository.HiringRepository
import com.onlineassignment.fetchrewards.challenge.domain.models.HiringItem
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GetGroupedHiringItemsUseCaseTest {

    private val repository: HiringRepository = mockk()
    private val useCase = GetGroupedHiringItemsUseCase(repository)

    @Test
    fun `invoke should return grouped, filtered, and sorted hiring items on success`() = runBlocking {
        val items = listOf(
            HiringItem(listId = 2, name = "item 2"),
            HiringItem(listId = 1, name = "item 280"),
            HiringItem(listId = 1, name = "item 28"),
            HiringItem(listId = 1, name = "item 276"),
            HiringItem(listId = 3)
        )

        // Mock the repository's fetchHiringList function
        coEvery { repository.fetchHiringList() } returns Result.success(items)
        val result = useCase()

        assertTrue(result.isSuccess)

        val groupedItems = result.getOrDefault(emptyMap())
        // Verify only 2 groups are present
        assertEquals(2, groupedItems.size)
        // Verify they listIds for the present groups
        assertTrue(groupedItems.contains(1))
        assertTrue(groupedItems.contains(2))
        // Verify sorted groups 1 - recall sorting it done on name which sorts in lexicographical order
        assertEquals(listOf("item 276", "item 28", "item 280"), groupedItems[1]?.map { it.name })
        // Verify sorted groups 2
        assertEquals(listOf("item 2"), groupedItems[2]?.map { it.name })
    }

    @Test
    fun `invoke should return failure on repository failure`() = runBlocking {
        val exception = Exception("beep-boop-derp-error")

        // Mock repository's fetchHiringList function for failure
        coEvery { repository.fetchHiringList() } returns Result.failure(exception)

        val result = useCase()

        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }

}
