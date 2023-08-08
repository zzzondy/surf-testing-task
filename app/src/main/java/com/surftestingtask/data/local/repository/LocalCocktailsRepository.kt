package com.surftestingtask.data.local.repository

import com.surftestingtask.data.local.models.CocktailDatabaseEntity
import com.surftestingtask.data.local.states.LocalAddingCocktailResult
import com.surftestingtask.data.local.states.LocalObtainingCocktailByIdResult
import com.surftestingtask.data.local.states.LocalObtainingCocktailsResult

interface LocalCocktailsRepository {

    suspend fun addCocktail(cocktailDatabaseEntity: CocktailDatabaseEntity): LocalAddingCocktailResult

    suspend fun getAllCocktails(): LocalObtainingCocktailsResult

    suspend fun getCocktailById(id: Long): LocalObtainingCocktailByIdResult
}