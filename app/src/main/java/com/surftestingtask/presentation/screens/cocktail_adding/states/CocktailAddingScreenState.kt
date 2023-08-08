package com.surftestingtask.presentation.screens.cocktail_adding.states

sealed class CocktailAddingScreenState {

    data class Content(
        val title: String,
        val description: String,
        val recipe: String,
        val ingredients: List<String>,
        val imageUri: String?,
        val isTitleError: Boolean,
        val isIngredientsError: Boolean,
    ) : CocktailAddingScreenState()
}
