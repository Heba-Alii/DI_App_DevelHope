package com.example.mealapp.data.repo

import com.example.mealapp.data.dataSource.remote.ApiService
import com.example.mealapp.domain.model.CategoryResponse
import com.example.mealapp.domain.repo.MealsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

class MealsRepoImpl(private val apiService: ApiService) : MealsRepo {
    override suspend fun getMealsFromRemote(): Flow<Response<CategoryResponse>> =
        flow {
            emit(apiService.getMeals())
        }.flowOn(Dispatchers.IO)
}