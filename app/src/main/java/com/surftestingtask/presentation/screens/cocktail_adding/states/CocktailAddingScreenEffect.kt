package com.surftestingtask.presentation.screens.cocktail_adding.states

sealed interface CocktailAddingScreenEffect {

    data object NavigateBack : CocktailAddingScreenEffect

    data object NavigateBackOnSuccessAdding : CocktailAddingScreenEffect

    data object ShowLoadingDialog : CocktailAddingScreenEffect

    data object HideLoadingDialog : CocktailAddingScreenEffect

    data object ShowIngredientAddingDialog : CocktailAddingScreenEffect
}