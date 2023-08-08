package com.surftestingtask.presentation.di

import com.surftestingtask.domain.repository.CocktailsRepository
import com.surftestingtask.domain.use_cases.AddCocktailUseCase
import com.surftestingtask.domain.use_cases.GetCocktailByIdUseCase
import com.surftestingtask.domain.use_cases.ObtainCocktailsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
class UseCaseModule {

    @ViewModelScoped
    @Provides
    fun provideAddCocktailUseCase(cocktailsRepository: CocktailsRepository): AddCocktailUseCase =
        AddCocktailUseCase(cocktailsRepository)

    @ViewModelScoped
    @Provides
    fun provideObtainCocktailsUseCase(cocktailsRepository: CocktailsRepository): ObtainCocktailsUseCase =
        ObtainCocktailsUseCase(cocktailsRepository)

    @ViewModelScoped
    @Provides
    fun provideGetCocktailByIdUseCase(cocktailsRepository: CocktailsRepository): GetCocktailByIdUseCase =
        GetCocktailByIdUseCase(cocktailsRepository)
}