package com.surftestingtask.presentation.screens.cocktail_details.states

import com.surftestingtask.domain.models.Cocktail

sealed class CocktailDetailsScreenState {

    data object Loading : CocktailDetailsScreenState()

    data class Content(val cocktail: Cocktail) : CocktailDetailsScreenState()

    data object Error : CocktailDetailsScreenState()
}
