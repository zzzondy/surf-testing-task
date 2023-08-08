package com.surftestingtask.data.local

object CocktailsDatabaseContract {

    const val DATABASE_NAME = "cocktails_database.db"

    object Cocktails {
        const val TABLE_NAME = "cocktails"

        const val ID = "id"
        const val TITLE = "title"
        const val INGREDIENTS = "ingredients"
        const val DESCRIPTION = "description"
        const val RECIPE = "recipe"
        const val IMAGE = "image"
    }
}