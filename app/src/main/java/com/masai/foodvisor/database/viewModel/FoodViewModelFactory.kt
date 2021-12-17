package com.masai.foodvisor.database.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.masai.foodvisor.database.repository.FoodRepository

class FoodViewModelFactory(private val foodRepository: FoodRepository)  : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FoodViewModel(foodRepository) as T
    }

}