package com.surftestingtask.domain.use_cases

import com.surftestingtask.domain.repository.CocktailsRepository

class GetCocktailByIdUseCase(private val cocktailsRepository: CocktailsRepository) {

    suspend operator fun invoke(id: Long) = cocktailsRepository.getCocktailById(id)
}