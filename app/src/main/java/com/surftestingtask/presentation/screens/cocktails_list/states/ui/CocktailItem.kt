package com.surftestingtask.presentation.screens.cocktails_list.states.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.coil.CoilImage
import com.surftestingtask.R
import com.surftestingtask.domain.models.Cocktail

@Composable
fun CocktailItem(
    cocktail: Cocktail,
    modifier: Modifier = Modifier,
    onCocktailClicked: (Long) -> Unit = {}
) {
    Box(
        modifier = modifier
            .clip(MaterialTheme.shapes.extraLarge)
            .size(180.dp)
            .clickable {
                onCocktailClicked(cocktail.id)
            },
        contentAlignment = Alignment.BottomCenter
    ) {
        if (cocktail.imageUri == null) {
            Box(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.outlineVariant,
                        shape = MaterialTheme.shapes.extraLarge
                    )
                    .size(180.dp),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    painter = painterResource(R.drawable.image_placeholder),
                    contentDescription = stringResource(
                        R.string.image_placeholder
                    )
                )
            }
        } else {
            CoilImage(
                imageModel = { cocktail.imageUri },
                modifier = Modifier
                    .clip(MaterialTheme.shapes.extraLarge)
                    .size(180.dp)
            )
        }

        Text(
            text = cocktail.title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 32.dp),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
        )
    }
}