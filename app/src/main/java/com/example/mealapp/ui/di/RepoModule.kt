package com.example.mealapp.ui.di

import com.example.mealapp.data.remote.ApiService
import com.example.mealapp.data.repo.MealsRepoImpl
import com.example.mealapp.domain.repo.MealsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {
    @Provides
    fun provideRepo(apiService: ApiService): MealsRepo {
        return MealsRepoImpl(apiService)
    }
}