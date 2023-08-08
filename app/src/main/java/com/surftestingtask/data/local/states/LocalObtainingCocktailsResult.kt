package com.surftestingtask.data.local.states

import com.surftestingtask.data.local.models.CocktailDatabaseEntity

sealed class LocalObtainingCocktailsResult {

    data class Success(val cocktails: List<CocktailDatabaseEntity>) :
        LocalObtainingCocktailsResult()

    data object Error : LocalObtainingCocktailsResult()
}
