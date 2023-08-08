package com.surftestingtask.presentation.screens.cocktail_details.states.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.coil.CoilImage
import com.surftestingtask.R
import com.surftestingtask.domain.models.Cocktail

@Composable
fun CocktailDetailsScreenContentState(
    cocktail: Cocktail,
    modifier: Modifier = Modifier,
    onEditClicked: () -> Unit = {}
) {

    Box(modifier = modifier) {
        CoilImage(
            imageModel = { cocktail.imageUri },
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.75f)
        )

        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
                .align(
                    Alignment.BottomCenter
                )
                .background(color = MaterialTheme.colorScheme.background)
        ) {


            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                item {
                    Text(
                        text = cocktail.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        style = MaterialTheme.typography.headlineMedium,
                        textAlign = TextAlign.Center
                    )
                }

                item {
                    if (!cocktail.description.isNullOrBlank()) {
                        Text(
                            text = cocktail.description,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            style = MaterialTheme.typography.labelLarge,
                            textAlign = TextAlign.Center
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                }

                items(cocktail.ingredients) {
                    Text(
                        text = it,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
                        style = MaterialTheme.typography.labelLarge,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = "-",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        style = MaterialTheme.typography.labelLarge,
                        textAlign = TextAlign.Center
                    )
                }

                item {
                    if (!cocktail.recipe.isNullOrBlank()) {
                        Text(
                            text = stringResource(R.string.recipe),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 16.dp),
                            style = MaterialTheme.typography.labelLarge,
                            textAlign = TextAlign.Center
                        )

                        Text(
                            text = cocktail.recipe,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                            style = MaterialTheme.typography.labelLarge,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            Button(
                onClick = onEditClicked,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                colors = ButtonDefaults.buttonColors(contentColor = MaterialTheme.colorScheme.background)
            ) {
                Text(
                    text = stringResource(R.string.edit),
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }
    }
}