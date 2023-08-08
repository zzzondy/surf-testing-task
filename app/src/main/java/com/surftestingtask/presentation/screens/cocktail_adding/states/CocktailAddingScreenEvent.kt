package com.surftestingtask.presentation.screens.cocktail_adding.states

sealed class CocktailAddingScreenEvent {

    data class OnImageChanged(val imageUri: String) : CocktailAddingScreenEvent()

    data class OnNameChanged(val newName: String) : CocktailAddingScreenEvent()

    data class OnDescriptionChanged(val newDescription: String) : CocktailAddingScreenEvent()

    data class OnRecipeChanged(val newRecipe: String) : CocktailAddingScreenEvent()

    data object OnAddIngredientClicked : CocktailAddingScreenEvent()

    data class OnAddIngredient(val ingredient: String) : CocktailAddingScreenEvent()

    data class OnDeleteIngredient(val ingredient: String) : CocktailAddingScreenEvent()

    data object OnSaveClicked : CocktailAddingScreenEvent()

    data object OnCancelClicked : CocktailAddingScreenEvent()
}
