package com.surftestingtask.presentation.di

import android.content.Context
import com.surftestingtask.data.local.database.CocktailsDao
import com.surftestingtask.data.local.database.CocktailsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideCocktailsDatabase(@ApplicationContext context: Context): CocktailsDatabase =
        CocktailsDatabase.create(context)

    @Singleton
    @Provides
    fun provideCocktailsDao(cocktailsDatabase: CocktailsDatabase): CocktailsDao =
        cocktailsDatabase.cocktailsDao
}