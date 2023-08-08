package com.surftestingtask.data.utils

import com.surftestingtask.data.local.models.CocktailDatabaseEntity
import com.surftestingtask.data.local.states.LocalAddingCocktailResult
import com.surftestingtask.data.local.states.LocalObtainingCocktailByIdResult
import com.surftestingtask.data.local.states.LocalObtainingCocktailsResult
import com.surftestingtask.domain.models.Cocktail
import com.surftestingtask.domain.states.AddingCocktailResult
import com.surftestingtask.domain.states.ObtainingCocktailByIdResult
import com.surftestingtask.domain.states.ObtainingCocktailsResult

fun LocalAddingCocktailResult.toDomain() =
    when (this) {
        is LocalAddingCocktailResult.Success -> AddingCocktailResult.Success

        is LocalAddingCocktailResult.Error -> AddingCocktailResult.Error
    }

fun LocalObtainingCocktailsResult.toDomain() =
    when (this) {
        is LocalObtainingCocktailsResult.Success -> ObtainingCocktailsResult.Success(this.cocktails.map { it.toDomain() })

        is LocalObtainingCocktailsResult.Error -> ObtainingCocktailsResult.Error
    }

fun LocalObtainingCocktailByIdResult.toDomain() =
    when (this) {
        is LocalObtainingCocktailByIdResult.Success -> ObtainingCocktailByIdResult.Success(this.cocktail.toDomain())

        is LocalObtainingCocktailByIdResult.Error -> ObtainingCocktailByIdResult.Error
    }

fun CocktailDatabaseEntity.toDomain() =
    Cocktail(id, title, ingredients, description, imageUri, recipe)

fun Cocktail.toData() = CocktailDatabaseEntity(
    title = title,
    description = description,
    recipe = recipe,
    imageUri = imageUri,
    ingredients = ingredients
)