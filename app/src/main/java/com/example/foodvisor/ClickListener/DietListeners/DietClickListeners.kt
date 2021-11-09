package com.example.foodvisor.ClickListener.DietListeners

import com.example.foodvisor.Model.DietModel.FitnessModel
import com.example.foodvisor.Model.DietModel.LifestyleModel
import com.example.foodvisor.Model.DietModel.WeightModel

interface DietClickListeners {

    fun onWeightItemClick(weightModel: WeightModel)

    fun onLifestyleItemClick(lifestyleModel: LifestyleModel)

    fun onFitnessItemClick(fitnessModel: FitnessModel)

}