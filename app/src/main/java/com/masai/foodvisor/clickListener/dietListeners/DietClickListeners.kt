package com.masai.foodvisor.clickListener.dietListeners

import com.masai.foodvisor.model.dietModel.FitnessModel
import com.masai.foodvisor.model.dietModel.LifestyleModel
import com.masai.foodvisor.model.dietModel.WeightModel

interface DietClickListeners {

    fun onWeightItemClick(weightModel: WeightModel)

    fun onLifestyleItemClick(lifestyleModel: LifestyleModel)

    fun onFitnessItemClick(fitnessModel: FitnessModel)

}