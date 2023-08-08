package com.surftestingtask.data.local.states

import com.surftestingtask.data.local.models.CocktailDatabaseEntity

sealed class LocalObtainingCocktailByIdResult {

    data class Success(val cocktail: CocktailDatabaseEntity) : LocalObtainingCocktailByIdResult()

    data object Error : LocalObtainingCocktailByIdResult()
}
