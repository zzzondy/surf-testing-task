package com.surftestingtask.presentation.screens.cocktail_details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.surftestingtask.presentation.screens.cocktail_details.states.CocktailDetailsScreenEvent
import com.surftestingtask.presentation.screens.cocktail_details.states.CocktailDetailsScreenState
import com.surftestingtask.presentation.screens.cocktail_details.states.ui.CocktailDetailsScreenContentState

@Composable
fun CocktailDetailsScreen(
    cocktailId: Long,
    navController: NavController,
    viewModel: CocktailDetailsScreenViewModel = hiltViewModel(),
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.onEvent(CocktailDetailsScreenEvent.OnEnterScreen(cocktailId))
    }

    CocktailDetailsScreenContent(
        state = viewModel.state.collectAsState().value,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun CocktailDetailsScreenContent(
    state: CocktailDetailsScreenState,
    onEvent: (CocktailDetailsScreenEvent) -> Unit
) {
    Scaffold { paddingValues ->
        when (state) {
            is CocktailDetailsScreenState.Content -> {
                CocktailDetailsScreenContentState(
                    cocktail = state.cocktail,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                )
            }

            else -> {

            }
        }
    }
}