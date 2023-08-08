package com.surftestingtask.presentation.screens.cocktail_adding

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.surftestingtask.domain.models.Cocktail
import com.surftestingtask.domain.states.AddingCocktailResult
import com.surftestingtask.domain.use_cases.AddCocktailUseCase
import com.surftestingtask.presentation.screens.cocktail_adding.states.CocktailAddingScreenEffect
import com.surftestingtask.presentation.screens.cocktail_adding.states.CocktailAddingScreenEvent
import com.surftestingtask.presentation.screens.cocktail_adding.states.CocktailAddingScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.internal.toImmutableList
import javax.inject.Inject

@HiltViewModel
class CocktailAddingScreenViewModel @Inject constructor(private val addCocktailUseCase: AddCocktailUseCase) :
    ViewModel() {

    private val _state = MutableStateFlow<CocktailAddingScreenState>(
        CocktailAddingScreenState.Content(
            title = "",
            description = "",
            recipe = "",
            ingredients = listOf(),
            imageUri = null,
            isTitleError = false,
            isIngredientsError = false,
        )
    )

    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<CocktailAddingScreenEffect>()

    val effect = _effect.asSharedFlow()

    fun onEvent(event: CocktailAddingScreenEvent) {
        when (event) {
            is CocktailAddingScreenEvent.OnImageChanged -> onImageChanged(event.imageUri)

            is CocktailAddingScreenEvent.OnNameChanged -> onNameChanged(event.newName)

            is CocktailAddingScreenEvent.OnDescriptionChanged -> onDescriptionChanged(event.newDescription)

            is CocktailAddingScreenEvent.OnRecipeChanged -> onRecipeChanged(event.newRecipe)

            is CocktailAddingScreenEvent.OnAddIngredientClicked -> onAddIngredientClicked()

            is CocktailAddingScreenEvent.OnAddIngredient -> onAddIngredient(event.ingredient)

            is CocktailAddingScreenEvent.OnDeleteIngredient -> onDeleteIngredient(event.ingredient)

            is CocktailAddingScreenEvent.OnSaveClicked -> onSaveClicked()

            is CocktailAddingScreenEvent.OnCancelClicked -> onCancelClicked()
        }
    }

    private fun onImageChanged(imageUri: String?) {
        viewModelScope.launch {
            if (state.value is CocktailAddingScreenState.Content) {
                _state.update {
                    (it as CocktailAddingScreenState.Content)
                        .copy(imageUri = imageUri)
                }
            }

            Log.d("Cocktails", state.value.toString())
        }
    }

    private fun onNameChanged(newName: String) {
        viewModelScope.launch {
            if (state.value is CocktailAddingScreenState.Content) {
                _state.update {
                    (it as CocktailAddingScreenState.Content)
                        .copy(
                            title = newName,
                            isTitleError = false
                        )
                }
            }
        }
    }

    private fun onDescriptionChanged(newDescription: String) {
        viewModelScope.launch {
            if (state.value is CocktailAddingScreenState.Content) {
                _state.update {
                    (it as CocktailAddingScreenState.Content).copy(description = newDescription)
                }
            }
        }
    }

    private fun onRecipeChanged(newRecipe: String) {
        viewModelScope.launch {
            if (state.value is CocktailAddingScreenState.Content) {
                _state.update {
                    (it as CocktailAddingScreenState.Content).copy(recipe = newRecipe)
                }
            }
        }
    }

    private fun onAddIngredientClicked() {
        viewModelScope.launch {
            _effect.emit(CocktailAddingScreenEffect.ShowIngredientAddingDialog)
        }
    }

    private fun onAddIngredient(ingredient: String) {
        viewModelScope.launch {
            if (state.value is CocktailAddingScreenState.Content) {
                _state.update {
                    val ingredients =
                        (it as CocktailAddingScreenState.Content).ingredients.toMutableList()
                    ingredients.add(ingredient)
                    it.copy(ingredients = ingredients.toImmutableList(), isIngredientsError = false)
                }
            }
        }
    }

    private fun onDeleteIngredient(ingredient: String) {
        viewModelScope.launch {
            if (state.value is CocktailAddingScreenState.Content) {
                _state.update {
                    val ingredients =
                        (it as CocktailAddingScreenState.Content).ingredients.toMutableList()
                    ingredients.remove(ingredient)
                    it.copy(ingredients = ingredients.toImmutableList())
                }
            }
        }
    }

    private fun onSaveClicked() {
        viewModelScope.launch(Dispatchers.IO) {
            if (state.value is CocktailAddingScreenState.Content) {
                val isTitleError =
                    (state.value as CocktailAddingScreenState.Content).title.isBlank()
                val isIngredientsError =
                    (state.value as CocktailAddingScreenState.Content).ingredients.isEmpty()

                if (isTitleError || isIngredientsError) {
                    _state.update {
                        (state.value as CocktailAddingScreenState.Content).copy(
                            isTitleError = isTitleError,
                            isIngredientsError = isIngredientsError
                        )
                    }
                } else {
                    _effect.emit(CocktailAddingScreenEffect.ShowLoadingDialog)
                    val cocktail = (state.value as CocktailAddingScreenState.Content)
                    val result = addCocktailUseCase(
                        Cocktail(
                            title = cocktail.title,
                            ingredients = cocktail.ingredients,
                            description = cocktail.description,
                            imageUri = cocktail.imageUri,
                            recipe = cocktail.recipe
                        )
                    )
                    if (result is AddingCocktailResult.Success) {
                        _effect.emit(CocktailAddingScreenEffect.NavigateBackOnSuccessAdding)
                    } else {
                        _effect.emit(CocktailAddingScreenEffect.HideLoadingDialog)
                    }
                }
            }
        }
    }

    private fun onCancelClicked() {
        viewModelScope.launch {
            _effect.emit(CocktailAddingScreenEffect.NavigateBack)
        }
    }
}