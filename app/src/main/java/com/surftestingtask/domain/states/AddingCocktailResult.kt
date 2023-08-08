package com.surftestingtask.domain.states

sealed class AddingCocktailResult {

    data object Success : AddingCocktailResult()

    data object Error : AddingCocktailResult()
}
