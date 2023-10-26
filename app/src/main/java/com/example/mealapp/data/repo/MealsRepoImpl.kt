package com.example.mealapp.data.repo

import com.example.mealapp.data.remote.ApiService
import com.example.mealapp.domain.model.CategoryResponse
import com.example.mealapp.domain.repo.MealsRepo

class MealsRepoImpl(private val apiService: ApiService) : MealsRepo {
    override suspend fun getMealsFromRemote(): CategoryResponse = apiService.getMeals()
}