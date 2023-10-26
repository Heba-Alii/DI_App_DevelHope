package com.example.mealapp.ui.themes

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealapp.domain.model.CategoryResponse
import com.example.mealapp.domain.useCase.GetMealzUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealsViewModel @Inject constructor(private val getMealzUseCase: GetMealzUseCase) :
    ViewModel() {
    private val _categories: MutableStateFlow<CategoryResponse?> =
        MutableStateFlow(null)
    public val categories: StateFlow<CategoryResponse?> = _categories

    fun getMeals() {
        viewModelScope.launch {
            try {
                _categories.value = getMealzUseCase()
                Log.e("TAG", "getMeals: $getMealzUseCase")
            } catch (e: Exception) {
                Log.e("TAG", "getMeals:${e.message.toString()}")
            }
        }

    }
}