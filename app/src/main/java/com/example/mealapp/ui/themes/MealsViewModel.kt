package com.example.mealapp.ui.themes

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealapp.domain.model.CategoryResponse
import com.example.mealapp.domain.useCase.GetMealzUseCase
import com.example.mealapp.ui.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealsViewModel @Inject constructor(private val getMealzUseCase: GetMealzUseCase) :
    ViewModel() {
    private val _categories: MutableStateFlow<DataState<CategoryResponse>?> =
        MutableStateFlow(null)
    public val categories: StateFlow<DataState<CategoryResponse>?> = _categories

    init {
        getMeals()
    }

    fun getMeals() {
        viewModelScope.launch {
            getMealzUseCase.getMeals().onStart {
                _categories.value = DataState.Loading(true)
            }.catch {
                _categories.value = DataState.Loading(false)
                _categories.value = DataState.Failure(it)
            }.collect {
                _categories.value = DataState.Loading(false)
                _categories.value = DataState.Success(it)
            }


        }
    }
}