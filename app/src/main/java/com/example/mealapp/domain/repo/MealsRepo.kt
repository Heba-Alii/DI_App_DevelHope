package com.example.mealapp.domain.repo

import com.example.mealapp.domain.model.CategoryResponse
import java.util.concurrent.Flow

interface MealsRepo {
    suspend fun getMealsFromRemote(): kotlinx.coroutines.flow.Flow<CategoryResponse>
}