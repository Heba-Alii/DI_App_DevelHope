package com.example.mealapp.domain.useCase

import com.example.mealapp.domain.repo.MealsRepo

class GetMealzUseCase(private val mealsRepo: MealsRepo) {
    suspend fun getMeals() = mealsRepo.getMealsFromRemote()
}