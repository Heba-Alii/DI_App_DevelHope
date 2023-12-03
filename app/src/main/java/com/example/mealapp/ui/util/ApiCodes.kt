package com.example.mealapp.ui.util

enum class ApiCodes(val code: Int) {
    SUCCESS(200),
    NotFound(404),
    CONNECTIONTimeOut(408),
    ERROR(0)
}