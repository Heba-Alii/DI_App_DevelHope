package com.example.mealapp.ui.themes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealapp.domain.model.CategoryResponse
import com.example.mealapp.domain.useCase.GetMealzUseCase
import com.example.mealapp.ui.util.ApiCodes
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
    private val _errorState: MutableStateFlow<ApiCodes?> = MutableStateFlow(null)
    public val errorState: StateFlow<ApiCodes?> = _errorState

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
            }.collect { response ->
                _categories.value = DataState.Loading(false)
                if (response.isSuccessful && response.body() != null && response.code() == ApiCodes.SUCCESS.code) {

                    _categories.value = DataState.Success(response.body()!!)
                } else if (
                    response.code() == ApiCodes.NotFound.code
                ) {
                    _errorState.value = ApiCodes.NotFound
                } else if (response.code() == ApiCodes.CONNECTIONTimeOut.code) {
                    _errorState.value = ApiCodes.CONNECTIONTimeOut
                } else if (response.code() == ApiCodes.ERROR.code) {
                    _errorState.value = ApiCodes.ERROR
                }
            }


        }
    }
}