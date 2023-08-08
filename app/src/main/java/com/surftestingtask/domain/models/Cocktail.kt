package com.surftestingtask.domain.models

data class Cocktail(
    val id: Long = -1,
    val title: String,
    val ingredients: List<String>,
    val description: String? = null,
    val imageUri: String? = null,
    val recipe: String? = null,
)
