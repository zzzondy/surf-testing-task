package com.surftestingtask.presentation.screens.cocktail_details.states

sealed class CocktailDetailsScreenEvent {

    data object OnEditClicked : CocktailDetailsScreenEvent()

    data class OnEnterScreen(val cocktailId: Long) : CocktailDetailsScreenEvent()
}
