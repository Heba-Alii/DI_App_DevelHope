package com.example.mealapp.domain.repo

import com.example.mealapp.domain.model.CategoryResponse

interface MealsRepo {
    suspend fun getMealsFromRemote(): CategoryResponse
}