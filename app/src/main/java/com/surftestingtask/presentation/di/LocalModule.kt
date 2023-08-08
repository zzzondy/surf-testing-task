package com.surftestingtask.presentation.di

import com.surftestingtask.data.local.database.CocktailsDao
import com.surftestingtask.data.local.repository.LocalCocktailsRepository
import com.surftestingtask.data.local.repository.LocalCocktailsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
class LocalModule {

    @ViewModelScoped
    @Provides
    fun provideLocalCocktailsRepository(cocktailsDao: CocktailsDao): LocalCocktailsRepository =
        LocalCocktailsRepositoryImpl(cocktailsDao)
}