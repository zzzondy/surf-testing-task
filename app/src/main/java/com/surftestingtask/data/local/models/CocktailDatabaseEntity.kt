package com.surftestingtask.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.surftestingtask.data.local.CocktailsDatabaseContract

@Entity(
    tableName = CocktailsDatabaseContract.Cocktails.TABLE_NAME,
    indices = [Index(CocktailsDatabaseContract.Cocktails.ID)]
)
data class CocktailDatabaseEntity(

    @ColumnInfo(name = CocktailsDatabaseContract.Cocktails.ID)
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = CocktailsDatabaseContract.Cocktails.TITLE)
    val title: String,

    @ColumnInfo(name = CocktailsDatabaseContract.Cocktails.DESCRIPTION)
    val description: String? = null,

    @ColumnInfo(name = CocktailsDatabaseContract.Cocktails.RECIPE)
    val recipe: String? = null,

    @ColumnInfo(name = CocktailsDatabaseContract.Cocktails.IMAGE)
    val imageUri: String? = null,

    @ColumnInfo(name = CocktailsDatabaseContract.Cocktails.INGREDIENTS)
    val ingredients: List<String>,
)

@ProvidedTypeConverter
class IngredientsConverter {

    @TypeConverter
    fun fromList(ingredients: List<String>): String = Gson().toJson(ingredients)


    @TypeConverter
    fun fromString(ingredients: String): List<String> {
        val type = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(ingredients, type)
    }
}