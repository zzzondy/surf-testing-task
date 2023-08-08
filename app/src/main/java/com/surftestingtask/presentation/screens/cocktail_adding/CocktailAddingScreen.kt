package com.surftestingtask.presentation.screens.cocktail_adding

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imeNestedScroll
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.skydoves.landscapist.coil.CoilImage
import com.surftestingtask.R
import com.surftestingtask.common.ui.LoadingDialog
import com.surftestingtask.common.utils.collectAsEffect
import com.surftestingtask.presentation.screens.cocktail_adding.states.CocktailAddingScreenEffect
import com.surftestingtask.presentation.screens.cocktail_adding.states.CocktailAddingScreenEvent
import com.surftestingtask.presentation.screens.cocktail_adding.states.CocktailAddingScreenState

@Composable
fun CocktailAddingScreen(
    navController: NavController,
    viewModel: CocktailAddingScreenViewModel = hiltViewModel(),
) {
    var isLoadingDialogVisible by rememberSaveable { mutableStateOf(false) }
    var isIngredientAddingDialogVisible by rememberSaveable { mutableStateOf(false) }

    viewModel.effect.collectAsEffect { effect ->
        when (effect) {
            CocktailAddingScreenEffect.NavigateBack -> navController.popBackStack()

            CocktailAddingScreenEffect.NavigateBackOnSuccessAdding -> {
                isLoadingDialogVisible = false
                navController.popBackStack()
            }

            CocktailAddingScreenEffect.ShowLoadingDialog -> isLoadingDialogVisible = true

            CocktailAddingScreenEffect.HideLoadingDialog -> isLoadingDialogVisible = false

            CocktailAddingScreenEffect.ShowIngredientAddingDialog -> isIngredientAddingDialogVisible =
                true
        }
    }

    if (isLoadingDialogVisible) {
        LoadingDialog()
    }

    if (isIngredientAddingDialogVisible) {
        IngredientAddingDialog(
            onDismissRequest = { isIngredientAddingDialogVisible = false },
            onSaveClicked = {
                viewModel.onEvent(CocktailAddingScreenEvent.OnAddIngredient(it))
                isIngredientAddingDialogVisible = false
            })
    }

    CocktailAddingScreenContent(
        state = viewModel.state.collectAsState().value,
        onEvent = viewModel::onEvent
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun CocktailAddingScreenContent(
    state: CocktailAddingScreenState,
    onEvent: (CocktailAddingScreenEvent) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val descriptionTextFieldFocusRequester = remember { FocusRequester() }

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            onEvent(CocktailAddingScreenEvent.OnImageChanged(uri.toString()))
        }
    )

    Scaffold { paddingValues ->
        when (state) {
            is CocktailAddingScreenState.Content -> {
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                        .pointerInput(Unit) {
                            detectTapGestures { focusManager.clearFocus() }
                        }
                        .imePadding()
                        .imeNestedScroll(),
                ) {
                    item(key = "image_section") {
                        Spacer(modifier = Modifier.height(16.dp))

                        if (state.imageUri == null) {
                            Box(
                                modifier = Modifier
                                    .background(
                                        color = MaterialTheme.colorScheme.outlineVariant,
                                        shape = MaterialTheme.shapes.extraLarge
                                    )
                                    .size(200.dp)
                                    .clickable {
                                        photoPickerLauncher.launch(
                                            PickVisualMediaRequest(
                                                ActivityResultContracts.PickVisualMedia.ImageOnly
                                            )
                                        )
                                    }
                                    .padding(horizontal = 16.dp),
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
                                imageModel = { state.imageUri },
                                modifier = Modifier
                                    .clip(MaterialTheme.shapes.extraLarge)
                                    .size(200.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    item(key = "title_section") {
                        OutlinedTextField(
                            value = state.title,
                            onValueChange = { onEvent(CocktailAddingScreenEvent.OnNameChanged(it)) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            shape = MaterialTheme.shapes.extraLarge,
                            label = {
                                Text(text = stringResource(R.string.title))
                            },
                            singleLine = true,
                            supportingText = {
                                Text(text = stringResource(R.string.add_title))
                            },
                            isError = state.isTitleError,
                            colors = TextFieldDefaults.colors(
                                focusedTextColor = MaterialTheme.colorScheme.onBackground,
                                focusedSupportingTextColor = MaterialTheme.colorScheme.outline,
                                focusedContainerColor = MaterialTheme.colorScheme.background,
                                unfocusedContainerColor = MaterialTheme.colorScheme.background,
                                unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
                                errorContainerColor = MaterialTheme.colorScheme.background,
                                errorTextColor = MaterialTheme.colorScheme.onBackground,
                            ),
                            keyboardActions = KeyboardActions(onGo = { descriptionTextFieldFocusRequester.requestFocus() }),
                            keyboardOptions = KeyboardOptions(
                                capitalization = KeyboardCapitalization.Characters,
                                imeAction = ImeAction.Go
                            )
                        )

                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    item(key = "description_section") {
                        OutlinedTextField(
                            value = state.description,
                            onValueChange = {
                                onEvent(
                                    CocktailAddingScreenEvent.OnDescriptionChanged(
                                        it
                                    )
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                                .focusRequester(descriptionTextFieldFocusRequester),
                            shape = MaterialTheme.shapes.extraLarge,
                            label = {
                                Text(text = stringResource(R.string.description))
                            },
                            supportingText = {
                                Text(text = stringResource(R.string.optional))
                            },
                            colors = TextFieldDefaults.colors(
                                focusedTextColor = MaterialTheme.colorScheme.onBackground,
                                focusedSupportingTextColor = MaterialTheme.colorScheme.outline,
                                focusedContainerColor = MaterialTheme.colorScheme.background,
                                unfocusedContainerColor = MaterialTheme.colorScheme.background,
                                unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
                                errorContainerColor = MaterialTheme.colorScheme.background,
                                errorTextColor = MaterialTheme.colorScheme.onBackground,
                            ),
                            maxLines = 10,
                        )

                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    item(key = "ingredients_section") {
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        ) {
                            items(state.ingredients) {
                                AssistChip(
                                    onClick = {},
                                    label = { Text(text = it) },
                                    shape = MaterialTheme.shapes.extraLarge,
                                    trailingIcon = {
                                        IconButton(
                                            onClick = {
                                                onEvent(
                                                    CocktailAddingScreenEvent.OnDeleteIngredient(
                                                        it
                                                    )
                                                )
                                            }
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Close,
                                                contentDescription = stringResource(
                                                    R.string.remove_icon
                                                ),
                                                tint = MaterialTheme.colorScheme.primary
                                            )
                                        }
                                    },
                                    colors = AssistChipDefaults.assistChipColors(labelColor = MaterialTheme.colorScheme.onBackground),
                                    border = AssistChipDefaults.assistChipBorder(borderColor = MaterialTheme.colorScheme.primary)
                                )

                                Spacer(modifier = Modifier.width(8.dp))
                            }

                            item {
                                FilledIconButton(
                                    onClick = {
                                        onEvent(
                                            CocktailAddingScreenEvent.OnAddIngredientClicked
                                        )
                                    },
                                    colors = IconButtonDefaults.filledIconButtonColors(
                                        contentColor = MaterialTheme.colorScheme.background,
                                        containerColor = if (state.isIngredientsError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
                                    )
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Add,
                                        contentDescription = stringResource(
                                            R.string.add_icon
                                        ),
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    item(key = "recipe_section") {
                        OutlinedTextField(
                            value = state.recipe,
                            onValueChange = {
                                onEvent(CocktailAddingScreenEvent.OnRecipeChanged(it))
                            },
                            label = {
                                Text(text = stringResource(R.string.recipe))
                            },
                            supportingText = {
                                Text(text = stringResource(R.string.optional))
                            },
                            shape = MaterialTheme.shapes.extraLarge,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            colors = TextFieldDefaults.colors(
                                focusedTextColor = MaterialTheme.colorScheme.onBackground,
                                focusedSupportingTextColor = MaterialTheme.colorScheme.outline,
                                focusedContainerColor = MaterialTheme.colorScheme.background,
                                unfocusedContainerColor = MaterialTheme.colorScheme.background,
                                unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
                                errorContainerColor = MaterialTheme.colorScheme.background,
                                errorTextColor = MaterialTheme.colorScheme.onBackground,
                            ),
                            maxLines = 10
                        )

                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    item(key = "buttons_section") {
                        Button(
                            onClick = { onEvent(CocktailAddingScreenEvent.OnSaveClicked) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(55.dp)
                                .padding(horizontal = 16.dp),
                            colors = ButtonDefaults.buttonColors(contentColor = MaterialTheme.colorScheme.background)
                        ) {
                            Text(
                                text = stringResource(R.string.save),
                                style = MaterialTheme.typography.headlineSmall
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedButton(
                            onClick = { onEvent(CocktailAddingScreenEvent.OnCancelClicked) },
                            border = BorderStroke(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.primary
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(55.dp)
                                .padding(horizontal = 16.dp),
                            colors = ButtonDefaults.outlinedButtonColors()
                        ) {
                            Text(
                                text = stringResource(R.string.cancel),
                                style = MaterialTheme.typography.headlineSmall
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }

    }

}