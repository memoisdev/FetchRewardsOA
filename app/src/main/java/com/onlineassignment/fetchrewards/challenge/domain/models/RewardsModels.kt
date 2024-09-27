package com.onlineassignment.fetchrewards.challenge.domain.models

/**
 * Domain model for a hiring item; this value is used through out the app to manipulate (e.g. sort),
 * and render in a view.
 */
data class HiringItem(
    val listId: Long,
    val name: String = ""
)
