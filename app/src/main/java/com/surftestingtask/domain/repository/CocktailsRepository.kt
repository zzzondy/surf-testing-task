package com.surftestingtask.domain.repository

import com.surftestingtask.domain.models.Cocktail
import com.surftestingtask.domain.states.AddingCocktailResult
import com.surftestingtask.domain.states.ObtainingCocktailByIdResult
import com.surftestingtask.domain.states.ObtainingCocktailsResult

interface CocktailsRepository {

    suspend fun addCocktail(cocktail: Cocktail): AddingCocktailResult

    suspend fun getAllCocktails(): ObtainingCocktailsResult

    suspend fun getCocktailById(id: Long): ObtainingCocktailByIdResult
}