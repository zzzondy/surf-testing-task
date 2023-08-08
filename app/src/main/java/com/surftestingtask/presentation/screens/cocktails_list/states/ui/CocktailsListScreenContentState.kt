package com.surftestingtask.presentation.screens.cocktails_list.states.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.surftestingtask.domain.models.Cocktail

@Composable
fun CocktailsListScreenContentState(
    cocktails: List<Cocktail>,
    modifier: Modifier = Modifier,
    onCocktailClicked: (Long) -> Unit = {},
) {
    LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = modifier) {
        items(cocktails, key = { it.id }) {
            CocktailItem(
                cocktail = it,
                modifier = Modifier.padding(vertical = 8.dp),
                onCocktailClicked = onCocktailClicked
            )
        }
    }
}