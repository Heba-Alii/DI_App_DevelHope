package com.example.mealapp.domain.repo

import com.example.mealapp.domain.model.CategoryResponse
import retrofit2.Response
import java.util.concurrent.Flow

interface MealsRepo {
    suspend fun getMealsFromRemote(): kotlinx.coroutines.flow.Flow<Response<CategoryResponse>>
}