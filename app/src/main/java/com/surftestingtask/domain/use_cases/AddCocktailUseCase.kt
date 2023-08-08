package com.surftestingtask.domain.use_cases

import com.surftestingtask.domain.models.Cocktail
import com.surftestingtask.domain.repository.CocktailsRepository

class AddCocktailUseCase(private val cocktailsRepository: CocktailsRepository) {

    suspend operator fun invoke(cocktail: Cocktail) = cocktailsRepository.addCocktail(cocktail)
}