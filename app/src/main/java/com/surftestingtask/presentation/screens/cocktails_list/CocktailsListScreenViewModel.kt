package com.surftestingtask.presentation.screens.cocktails_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.surftestingtask.domain.states.ObtainingCocktailsResult
import com.surftestingtask.domain.use_cases.ObtainCocktailsUseCase
import com.surftestingtask.presentation.screens.cocktails_list.states.CocktailsListScreenEffect
import com.surftestingtask.presentation.screens.cocktails_list.states.CocktailsListScreenEvent
import com.surftestingtask.presentation.screens.cocktails_list.states.CocktailsListScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CocktailsListScreenViewModel @Inject constructor(private val obtainCocktailsUseCase: ObtainCocktailsUseCase) :
    ViewModel() {

    private val _effect = MutableSharedFlow<CocktailsListScreenEffect>()
    val effect = _effect.asSharedFlow()

    private val _state =
        MutableStateFlow<CocktailsListScreenState>(CocktailsListScreenState.Loading)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update {
                when (val result = obtainCocktailsUseCase()) {
                    is ObtainingCocktailsResult.Success -> {
                        if (result.cocktails.isEmpty()) {
                            CocktailsListScreenState.Empty
                        } else {
                            CocktailsListScreenState.Content(result.cocktails)
                        }
                    }

                    is ObtainingCocktailsResult.Error -> CocktailsListScreenState.Error
                }
            }
        }
    }

    fun onEvent(event: CocktailsListScreenEvent) {
        when (event) {
            is CocktailsListScreenEvent.AddNewCocktailClicked -> onAddNewCocktailClicked()

            is CocktailsListScreenEvent.OnCocktailClicked -> onCocktailClicked(event.id)
        }
    }

    private fun onAddNewCocktailClicked() {
        viewModelScope.launch {
            _effect.emit(CocktailsListScreenEffect.NavigateToAddingCocktailScreen)
        }
    }

    private fun onCocktailClicked(id: Long) {
        viewModelScope.launch {
            _effect.emit(CocktailsListScreenEffect.NavigateToCocktailDetailsScreen(id))
        }
    }
}