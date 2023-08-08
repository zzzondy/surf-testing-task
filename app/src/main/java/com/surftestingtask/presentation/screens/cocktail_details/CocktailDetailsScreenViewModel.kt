package com.surftestingtask.presentation.screens.cocktail_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.surftestingtask.domain.states.ObtainingCocktailByIdResult
import com.surftestingtask.domain.use_cases.GetCocktailByIdUseCase
import com.surftestingtask.presentation.screens.cocktail_details.states.CocktailDetailsScreenEffect
import com.surftestingtask.presentation.screens.cocktail_details.states.CocktailDetailsScreenEvent
import com.surftestingtask.presentation.screens.cocktail_details.states.CocktailDetailsScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CocktailDetailsScreenViewModel @Inject constructor(private val getCocktailByIdUseCase: GetCocktailByIdUseCase) :
    ViewModel() {

    private val _state =
        MutableStateFlow<CocktailDetailsScreenState>(CocktailDetailsScreenState.Loading)
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<CocktailDetailsScreenEffect>()
    val effect = _effect.asSharedFlow()

    fun onEvent(event: CocktailDetailsScreenEvent) {
        when (event) {
            is CocktailDetailsScreenEvent.OnEnterScreen -> onEnterScreen(event.cocktailId)

            is CocktailDetailsScreenEvent.OnEditClicked -> onEditClicked()
        }
    }

    private fun onEnterScreen(cocktailId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update {
                when (val result = getCocktailByIdUseCase(cocktailId)) {
                    is ObtainingCocktailByIdResult.Success -> CocktailDetailsScreenState.Content(
                        result.cocktail
                    )

                    is ObtainingCocktailByIdResult.Error -> CocktailDetailsScreenState.Error
                }
            }
        }
    }


    private fun onEditClicked() {
        viewModelScope.launch {
            _effect.emit(CocktailDetailsScreenEffect.NavigateToCocktailEditScreen)
        }
    }
}