package com.onlineassignment.fetchrewards.challenge.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onlineassignment.fetchrewards.challenge.domain.models.HiringItem
import com.onlineassignment.fetchrewards.challenge.domain.usecase.GetGroupedHiringItemsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HiringViewModel(
    private val groupedHiringItemsUseCase: GetGroupedHiringItemsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<HiringUiState<Map<Long, List<HiringItem>>>>(HiringUiState.Loading)
    val uiState: StateFlow<HiringUiState<Map<Long, List<HiringItem>>>> = _uiState

    init {
        fetchGroupedHiringItems()
    }

    fun fetchGroupedHiringItems() {
        _uiState.value = HiringUiState.Loading
        viewModelScope.launch {
            groupedHiringItemsUseCase().fold(
                onSuccess = {
                    _uiState.value = HiringUiState.Success(it)
                },
                onFailure = {
                    _uiState.value = HiringUiState.Error
                }
            )
        }
    }

}
