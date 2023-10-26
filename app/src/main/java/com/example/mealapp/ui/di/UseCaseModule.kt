package com.example.mealapp.ui.di

import com.example.mealapp.domain.repo.MealsRepo
import com.example.mealapp.domain.useCase.GetMealzUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideUseCase(mealsRepo: MealsRepo): GetMealzUseCase {
        return GetMealzUseCase(mealsRepo)
    }
}