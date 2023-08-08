package com.surftestingtask.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.surftestingtask.data.local.CocktailsDatabaseContract
import com.surftestingtask.data.local.models.CocktailDatabaseEntity

@Dao
interface CocktailsDao {

    @Insert
    fun insertCocktail(cocktailDatabaseEntity: CocktailDatabaseEntity): Long

    @Query("SELECT * FROM ${CocktailsDatabaseContract.Cocktails.TABLE_NAME}")
    fun getAllCocktails(): List<CocktailDatabaseEntity>

    @Query("SELECT * FROM ${CocktailsDatabaseContract.Cocktails.TABLE_NAME} WHERE ${CocktailsDatabaseContract.Cocktails.ID} = :id")
    fun getCocktailById(id: Long): CocktailDatabaseEntity
}