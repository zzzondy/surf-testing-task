package com.surftestingtask.presentation.screens.cocktails_list.states

sealed interface CocktailsListScreenEffect {

    data object NavigateToAddingCocktailScreen : CocktailsListScreenEffect

    data class NavigateToCocktailDetailsScreen(val id: Long) : CocktailsListScreenEffect
}