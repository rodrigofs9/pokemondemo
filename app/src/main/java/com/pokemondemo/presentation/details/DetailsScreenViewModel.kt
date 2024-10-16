package com.pokemondemo.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pokemondemo.navigation.model.DetailsArgs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailsScreenViewModel(private val args: DetailsArgs) : ViewModel() {

    private val _uiState: MutableStateFlow<DetailsScreenState> = MutableStateFlow(
        DetailsScreenState()
    )
    val uiState get() = _uiState.asStateFlow()

    init {
        getPokemonDetails()
    }

    private fun getPokemonDetails() {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    name = args.name,
                    imageUrl = args.imageUrl,
                    description = args.description,
                )
            }
        }
    }
}