package com.surftestingtask.presentation.screens.cocktails_list.states

sealed class CocktailsListScreenEvent {

    data object AddNewCocktailClicked : CocktailsListScreenEvent()

    data class OnCocktailClicked(val id: Long) : CocktailsListScreenEvent()
}