package com.surftestingtask.presentation.di

import com.surftestingtask.data.local.repository.LocalCocktailsRepository
import com.surftestingtask.data.repository.CocktailsRepositoryImpl
import com.surftestingtask.domain.repository.CocktailsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
class RepositoryModule {


    @ViewModelScoped
    @Provides
    fun provideCocktailsRepository(localCocktailsRepository: LocalCocktailsRepository): CocktailsRepository =
        CocktailsRepositoryImpl(localCocktailsRepository)
}