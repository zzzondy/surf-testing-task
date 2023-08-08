package com.surftestingtask.presentation.screens.cocktails_list.states

import com.surftestingtask.domain.models.Cocktail

sealed class CocktailsListScreenState {

    data class Content(val cocktails: List<Cocktail>) : CocktailsListScreenState()

    data object Empty : CocktailsListScreenState()

    data object Error : CocktailsListScreenState()

    data object Loading : CocktailsListScreenState()
}
