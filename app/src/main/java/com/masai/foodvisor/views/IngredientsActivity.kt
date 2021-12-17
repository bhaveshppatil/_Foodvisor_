package com.masai.foodvisor.views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.masai.foodvisor.R
import kotlinx.android.synthetic.main.activity_ingredients.*

class IngredientsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredients)
        supportActionBar?.hide()

        var intent = Intent()
        intent = getIntent()
        val ArticleNameB = intent.getStringExtra("ArticleNameB")
        val CaloriesB = intent.getStringExtra("CaloriesB")
        val MinutesB = intent.getStringExtra("MinutesB")
        val PreparationB = intent.getStringExtra("PreparationB")
        val IngredientsB = intent.getStringExtra("IngredientsB")
        val ImagesB = intent.getStringExtra("ImagesB")

        Glide.with(ivRecipe).load(ImagesB).into(ivRecipe)
        tvRecipeName.text = ArticleNameB
        tvCalories.text = "$CaloriesB Kcal"
        tvCookTime.text = "$MinutesB \n min"
        tvDifficulty.text = "Easy \nDifficulty"
        tvPreparation.text = "$PreparationB \nPrep Time"

        btnRecipe.setOnClickListener {
            tvIngredients.text = IngredientsB
        }

        btnNutritionalInfo.setOnClickListener {
            tvIngredients.text = "No Data Found"
        }
    }
}