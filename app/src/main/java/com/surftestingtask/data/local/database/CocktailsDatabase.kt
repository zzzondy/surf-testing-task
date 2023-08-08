package com.surftestingtask.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.surftestingtask.data.local.CocktailsDatabaseContract
import com.surftestingtask.data.local.models.CocktailDatabaseEntity
import com.surftestingtask.data.local.models.IngredientsConverter

@Database(entities = [CocktailDatabaseEntity::class], version = 1)
@TypeConverters(IngredientsConverter::class)
abstract class CocktailsDatabase : RoomDatabase() {

    abstract val cocktailsDao: CocktailsDao

    companion object {
        fun create(context: Context): CocktailsDatabase =
            Room.databaseBuilder(
                context,
                CocktailsDatabase::class.java,
                CocktailsDatabaseContract.DATABASE_NAME
            )
                .fallbackToDestructiveMigration()
                .addTypeConverter(IngredientsConverter())
                .build()
    }
}