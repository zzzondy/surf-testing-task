package com.surftestingtask.domain.use_cases

import com.surftestingtask.domain.repository.CocktailsRepository

class ObtainCocktailsUseCase(private val cocktailsRepository: CocktailsRepository) {

    suspend operator fun invoke() = cocktailsRepository.getAllCocktails()
}