package com.pokemondemo.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pokemondemo.navigation.model.DetailsArgs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailsScreenViewModel : ViewModel() {

    private val _uiState: MutableStateFlow<DetailsScreenState> = MutableStateFlow(
        DetailsScreenState()
    )
    val uiState get() = _uiState.asStateFlow()

    fun getPokemonDetails(id: String) {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    name = id,
                    imageUrl = "",
                    description = "teeste descricaoooo",
                )
            }
        }
    }
}