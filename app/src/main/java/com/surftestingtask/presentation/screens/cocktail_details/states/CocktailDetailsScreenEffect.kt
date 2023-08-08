package com.surftestingtask.presentation.screens.cocktail_details.states

sealed interface CocktailDetailsScreenEffect {

    data object NavigateToCocktailEditScreen : CocktailDetailsScreenEffect
}
