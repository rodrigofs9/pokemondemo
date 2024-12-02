package com.marveldemo.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailsScreenViewModel : ViewModel() {

    private val _uiState: MutableStateFlow<DetailsScreenState> = MutableStateFlow(
        DetailsScreenState()
    )
    val uiState get() = _uiState.asStateFlow()

    fun getPokemonDetails(name: String) {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    name = name,
                    imageUrl = "",
                    description = "teeste descricaoooo",
                )
            }
        }
    }
}