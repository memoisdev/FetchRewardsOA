package com.onlineassignment.fetchrewards.challenge.viewmodel

/**
 * Sealed class representing the different states of the Hiring UI.
 */
sealed class HiringUiState<out T> {
    /**
     * Denotes we're loading data.
     */
    data object Loading : HiringUiState<Nothing>()

    /**
     * This state represents a successful data load and will contain the data.
     */
    data class Success<out T>(val data: T) : HiringUiState<T>()

    /**
     * Generic error to represent we've encountered an error while loading data.
     */
    data object Error : HiringUiState<Nothing>()
}
