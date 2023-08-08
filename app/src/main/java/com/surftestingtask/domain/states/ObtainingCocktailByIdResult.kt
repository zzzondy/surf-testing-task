package com.surftestingtask.domain.states

import com.surftestingtask.domain.models.Cocktail

sealed class ObtainingCocktailByIdResult {

    data class Success(val cocktail: Cocktail) : ObtainingCocktailByIdResult()

    data object Error : ObtainingCocktailByIdResult()
}