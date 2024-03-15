package com.example.mealapp.ui.themes

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.mealapp.databinding.ActivityMainBinding
import com.example.mealapp.ui.util.ApiCodes
import com.example.mealapp.ui.util.DataState
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mealsViewModel: MealsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mealsViewModel.getMeals()
        startDataLoading()
    }

    private fun startDataLoading() {
        binding.progree.visibility = View.VISIBLE

        val mealsAdapter = MealsAdapter()
        lifecycleScope.launchWhenStarted {
            mealsViewModel.categories.collect() {
                when (it) {
                    is DataState.Failure -> it.throwable.message
                    is DataState.Loading -> binding.progree.visibility = View.VISIBLE
                    is DataState.Success -> {
                        binding.progree.visibility = View.GONE
                        mealsAdapter.submitList(it.data.categories)
                        binding.recycler.adapter = mealsAdapter
                    }

                    null -> null
                    else -> {
                        "Please try again"
                    }
                }
            }

        }
        lifecycleScope.launch {
            mealsViewModel.errorState.collect() {
                when (it) {
                    ApiCodes.SUCCESS -> {
                        Snackbar.make(binding.root, "Success", Snackbar.LENGTH_SHORT).show()
                    }

                    ApiCodes.NotFound -> Snackbar.make(
                        binding.root,
                        "Not Found",
                        Snackbar.LENGTH_SHORT
                    ).show()

                    ApiCodes.CONNECTIONTimeOut -> Snackbar.make(
                        binding.root,
                        "Request TIme Out",
                        Snackbar.LENGTH_SHORT
                    ).show()

                    ApiCodes.ERROR -> Snackbar.make(binding.root, "Error", Snackbar.LENGTH_SHORT)
                        .show()

                    null -> null
                }
            }
        }
    }

}