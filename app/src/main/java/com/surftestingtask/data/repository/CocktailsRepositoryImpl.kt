package com.surftestingtask.data.repository

import com.surftestingtask.data.local.repository.LocalCocktailsRepository
import com.surftestingtask.data.utils.toData
import com.surftestingtask.data.utils.toDomain
import com.surftestingtask.domain.models.Cocktail
import com.surftestingtask.domain.repository.CocktailsRepository
import com.surftestingtask.domain.states.AddingCocktailResult
import com.surftestingtask.domain.states.ObtainingCocktailByIdResult
import com.surftestingtask.domain.states.ObtainingCocktailsResult

class CocktailsRepositoryImpl(private val localCocktailsRepository: LocalCocktailsRepository) :
    CocktailsRepository {

    override suspend fun addCocktail(cocktail: Cocktail): AddingCocktailResult =
        localCocktailsRepository.addCocktail(cocktail.toData()).toDomain()

    override suspend fun getAllCocktails(): ObtainingCocktailsResult =
        localCocktailsRepository.getAllCocktails().toDomain()

    override suspend fun getCocktailById(id: Long): ObtainingCocktailByIdResult =
        localCocktailsRepository.getCocktailById(id).toDomain()
}