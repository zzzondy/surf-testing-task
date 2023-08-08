package com.surftestingtask.presentation.screens.cocktails_list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.surftestingtask.R
import com.surftestingtask.common.utils.collectAsEffect
import com.surftestingtask.presentation.navigation.CocktailsAppScreens
import com.surftestingtask.presentation.screens.cocktails_list.states.CocktailsListScreenEffect
import com.surftestingtask.presentation.screens.cocktails_list.states.CocktailsListScreenEvent
import com.surftestingtask.presentation.screens.cocktails_list.states.CocktailsListScreenState
import com.surftestingtask.presentation.screens.cocktails_list.states.ui.CocktailsListScreenContentState
import com.surftestingtask.presentation.screens.cocktails_list.states.ui.CocktailsListScreenEmptyState

@Composable
fun CocktailsListScreen(
    navController: NavController,
    viewModel: CocktailsListScreenViewModel = hiltViewModel(),
) {
    viewModel.effect.collectAsEffect { effect ->
        when (effect) {
            is CocktailsListScreenEffect.NavigateToAddingCocktailScreen -> {
                navController.navigate(CocktailsAppScreens.CocktailAddingScreen.route)
            }

            is CocktailsListScreenEffect.NavigateToCocktailDetailsScreen -> {
                navController.navigate(
                    CocktailsAppScreens.CocktailDetailsScreen.passArguments(
                        effect.id
                    )
                )
            }
        }
    }

    CocktailsListScreenContent(
        state = viewModel.state.collectAsState().value,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun CocktailsListScreenContent(
    state: CocktailsListScreenState,
    onEvent: (CocktailsListScreenEvent) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onEvent(CocktailsListScreenEvent.AddNewCocktailClicked) },
                shape = CircleShape,
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.background,
                modifier = Modifier.size(80.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.add_icon)
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { paddingValues ->
        when (state) {
            is CocktailsListScreenState.Content -> {
                CocktailsListScreenContentState(
                    cocktails = state.cocktails,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(horizontal = 8.dp),
                    onCocktailClicked = { onEvent(CocktailsListScreenEvent.OnCocktailClicked(it)) }
                )
            }

            is CocktailsListScreenState.Empty -> CocktailsListScreenEmptyState(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                onAddCocktailClicked = { onEvent(CocktailsListScreenEvent.AddNewCocktailClicked) }
            )

            else -> {

            }
        }
    }

}