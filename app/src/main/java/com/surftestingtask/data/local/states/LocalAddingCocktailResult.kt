package com.surftestingtask.data.local.states

sealed class LocalAddingCocktailResult {

    data object Success : LocalAddingCocktailResult()

    data object Error : LocalAddingCocktailResult()
}
