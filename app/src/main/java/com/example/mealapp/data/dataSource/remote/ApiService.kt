package com.example.mealapp.data.dataSource.remote

import com.example.mealapp.domain.model.CategoryResponse
import retrofit2.http.GET

interface ApiService {
    @GET("categories.php")
    suspend fun getMeals(): CategoryResponse
}