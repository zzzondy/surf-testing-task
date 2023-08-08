package com.surftestingtask.domain.states

import com.surftestingtask.domain.models.Cocktail

sealed class ObtainingCocktailsResult {

    data class Success(val cocktails: List<Cocktail>) : ObtainingCocktailsResult()

    data object Error : ObtainingCocktailsResult()
}
