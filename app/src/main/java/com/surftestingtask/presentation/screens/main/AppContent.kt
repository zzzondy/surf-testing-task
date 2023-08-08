package com.surftestingtask.presentation.screens.main

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.surftestingtask.presentation.navigation.CocktailIdArgument
import com.surftestingtask.presentation.navigation.CocktailsAppScreens
import com.surftestingtask.presentation.screens.cocktail_adding.CocktailAddingScreen
import com.surftestingtask.presentation.screens.cocktail_details.CocktailDetailsScreen
import com.surftestingtask.presentation.screens.cocktails_list.CocktailsListScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppContent() {
    val navController = rememberAnimatedNavController()

    AnimatedNavHost(
        navController = navController,
        startDestination = CocktailsAppScreens.CocktailsListScreen.route,
    ) {
        composable(route = CocktailsAppScreens.CocktailsListScreen.route) {
            CocktailsListScreen(navController = navController)
        }

        composable(route = CocktailsAppScreens.CocktailAddingScreen.route) {
            CocktailAddingScreen(navController = navController)
        }

        composable(
            route = CocktailsAppScreens.CocktailDetailsScreen.route,
            arguments = listOf(
                navArgument(CocktailIdArgument) {
                    type = NavType.LongType
                }
            )
        ) {
            CocktailDetailsScreen(
                cocktailId = it.arguments?.getLong(CocktailIdArgument)!!,
                navController = navController
            )
        }
    }
}