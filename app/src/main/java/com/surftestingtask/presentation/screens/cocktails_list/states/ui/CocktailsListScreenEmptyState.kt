package com.surftestingtask.presentation.screens.cocktails_list.states.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.surftestingtask.R

@Composable
fun CocktailsListScreenEmptyState(
    modifier: Modifier = Modifier,
    onAddCocktailClicked: () -> Unit = {},
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(R.drawable.summer_holidays),
            contentDescription = stringResource(
                R.string.image
            ),
            modifier = Modifier
                .size(283.dp)
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))


        Text(
            text = stringResource(R.string.my_cocktails),
            style = MaterialTheme.typography.displayMedium,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.add_your_first_cocktail_here),
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.outline,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Icon(
            painter = painterResource(R.drawable.arrow),
            contentDescription = stringResource(R.string.arrow_icon)
        )

        Spacer(modifier = Modifier.height(8.dp))


        FloatingActionButton(
            onClick = onAddCocktailClicked,
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
    }
}