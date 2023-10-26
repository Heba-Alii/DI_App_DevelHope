package com.example.mealapp.ui.themes

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.mealapp.databinding.ActivityMainBinding
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
        binding.progree.visibility=View.VISIBLE

        val mealsAdapter = MealsAdapter()
        lifecycleScope.launch {
            mealsViewModel.categories.collect{
                mealsAdapter.submitList(it?.categories)
                binding.recycler.adapter = mealsAdapter

            }

        }

    }

}