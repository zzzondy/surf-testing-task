package com.surftestingtask.presentation.navigation

sealed class CocktailsAppScreens(val route: String) {

    data object CocktailsListScreen : CocktailsAppScreens(route = "cocktails_list_screen")

    data object CocktailAddingScreen :
        CocktailsAppScreens(route = "cocktail_adding_screen")

    data object CocktailDetailsScreen :
        CocktailsAppScreens(route = "cocktail_details_screen/{${CocktailIdArgument}}") {
        fun passArguments(id: Long): String {
            return this.route.replace("{${CocktailIdArgument}}", id.toString())
        }
    }
}

const val CocktailIdArgument = "id"
