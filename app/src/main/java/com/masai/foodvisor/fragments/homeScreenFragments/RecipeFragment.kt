package com.masai.foodvisor.fragments.homeScreenFragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.masai.foodvisor.model.*
import com.masai.foodvisor.R
import com.masai.foodvisor.views.IngredientsActivity
import com.masai.foodvisor.views.PremiumUnlocked
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_recipe.*
import java.io.InputStream


class RecipeFragment : Fragment(R.layout.fragment_recipe),
    com.masai.foodvisor.clickListener.Clicklistener {

    private var breakfastModel = mutableListOf<com.masai.foodvisor.model.BreakfastModel>()
    private lateinit var breakfastAdapter: com.masai.foodvisor.adapter.BreakfastAdapter

    private var lunchModelList = mutableListOf<com.masai.foodvisor.model.LunchModel>()
    private lateinit var lunchAdapter: com.masai.foodvisor.adapter.LunchAdapter

    private var dinnerModelList = mutableListOf<com.masai.foodvisor.model.DinnerModel>()
    private lateinit var dinnerAdapter: com.masai.foodvisor.adapter.DinnerAdapter

    private var drinksModelList = mutableListOf<com.masai.foodvisor.model.DrinksModel>()
    private lateinit var drinksAdapter: com.masai.foodvisor.adapter.DrinksAdapter

    private val runnable = Runnable {
        readBreakfastJson()
        readLunchJson()
        readDinnerJson()
        readDrinksJson()
    }

    private fun readBreakfastJson() {
        try {
            var inputStream: InputStream = context?.assets?.open("breakfast.json")!!
            var data = inputStream.read()
            var builder: StringBuilder = StringBuilder()
            while (data != -1) {
                val ch = data.toChar()
                builder.append(ch)
                data = inputStream.read()
            }
            buildBreakfastPojoFromJson(builder.toString())

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun readDrinksJson() {
        try {
            var inputStream: InputStream = context?.assets?.open("drinks.json")!!
            var data = inputStream.read()
            var builder: StringBuilder = StringBuilder()
            while (data != -1) {
                val ch = data.toChar()
                builder.append(ch)
                data = inputStream.read()
            }
            buildDrinksPojoFromJson(builder.toString())

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun readDinnerJson() {
        try {
            var inputStream: InputStream = context?.assets?.open("dinner.json")!!
            var data = inputStream.read()
            var builder: StringBuilder = StringBuilder()
            while (data != -1) {
                val ch = data.toChar()
                builder.append(ch)
                data = inputStream.read()
            }
            buildDinnerFromJson(builder.toString())

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun readLunchJson() {
        try {
            var inputStream: InputStream = context?.assets?.open("lunch.json")!!
            var data = inputStream.read()
            var builder: StringBuilder = StringBuilder()
            while (data != -1) {
                val ch = data.toChar()
                builder.append(ch)
                data = inputStream.read()
            }
            buildLunchPojoFromJson(builder.toString())

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun startBreakfastBackground() {
        val thread = Thread(runnable)
        thread.start()
    }

    private fun buildBreakfastPojoFromJson(json: String) {
        val type = object : TypeToken<com.masai.foodvisor.model.Breakfast>() {}.type
        val breakfast: com.masai.foodvisor.model.Breakfast = Gson().fromJson(json, type)
        breakfastModel = breakfast.breakfast as MutableList<com.masai.foodvisor.model.BreakfastModel>
        updateBreakfastUi()
    }

    private fun buildDrinksPojoFromJson(json: String) {
        val type = object : TypeToken<com.masai.foodvisor.model.Drinks>() {}.type
        val drinks: com.masai.foodvisor.model.Drinks = Gson().fromJson(json, type)
        drinksModelList = drinks.drinksModels as MutableList<com.masai.foodvisor.model.DrinksModel>
        updateDrinksUi()
    }

    private fun buildDinnerFromJson(json: String) {
        val type = object : TypeToken<com.masai.foodvisor.model.Dinner>() {}.type
        val dinner: com.masai.foodvisor.model.Dinner = Gson().fromJson(json, type)
        dinnerModelList = dinner.dinnerModels as MutableList<com.masai.foodvisor.model.DinnerModel>
        updateDinnerUI()
    }

    private fun buildLunchPojoFromJson(json: String) {
        val type = object : TypeToken<com.masai.foodvisor.model.Lunch>() {}.type
        val lunch: com.masai.foodvisor.model.Lunch = Gson().fromJson(json, type)
        lunchModelList = lunch.lunchModels as MutableList<com.masai.foodvisor.model.LunchModel>
        updateLunchUi()
    }

    private fun updateBreakfastUi() {
        activity?.runOnUiThread {
            breakfastAdapter.updateBreakfastData(breakfastModel)
        }
    }

    private fun updateDrinksUi() {
        activity?.runOnUiThread {
            drinksAdapter.updateDrinksData(drinksModelList)
        }
    }

    private fun updateLunchUi() {
        activity?.runOnUiThread {
            lunchAdapter.updateLunchData(lunchModelList)
        }
    }

    private fun updateDinnerUI() {
        activity?.runOnUiThread {
            dinnerAdapter.updateDinnerData(dinnerModelList)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnPremiumUnlocked.setOnClickListener {
            val intent = Intent(context, PremiumUnlocked::class.java)
            startActivity(intent)
        }

        val breakfast_recycler_view = view.findViewById<RecyclerView>(R.id.recycler_view_breakfast)
        breakfastAdapter = context?.let {
            com.masai.foodvisor.adapter.BreakfastAdapter(
                it,
                breakfastModel,
                this
            )
        }!!
        breakfast_recycler_view.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        breakfast_recycler_view.adapter = breakfastAdapter

        val lunch_recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_lunch)
        lunchAdapter = context?.let {
            com.masai.foodvisor.adapter.LunchAdapter(
                it,
                lunchModelList,
                this
            )
        }!!
        lunch_recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        lunch_recyclerView.adapter = lunchAdapter

        val dinner_recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_Dinner)
        dinnerAdapter = context?.let {
            com.masai.foodvisor.adapter.DinnerAdapter(
                it,
                dinnerModelList,
                this
            )
        }!!
        dinner_recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        dinner_recyclerView.adapter = dinnerAdapter

        val drinks_recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_drinks)
        drinksAdapter = context?.let {
            com.masai.foodvisor.adapter.DrinksAdapter(
                it,
                drinksModelList,
                this
            )
        }!!
        drinks_recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        drinks_recyclerView.adapter = drinksAdapter

        startBreakfastBackground()
    }

    override fun onBreakfastRecipeClick(breakfastModel: com.masai.foodvisor.model.BreakfastModel) {
        val intent = Intent(context, IngredientsActivity::class.java)
        intent.putExtra("ArticleNameB", breakfastModel.articleName)
        intent.putExtra("CaloriesB", breakfastModel.calories)
        intent.putExtra("MinutesB", breakfastModel.minutes)
        intent.putExtra("PreparationB", breakfastModel.preparation)
        intent.putExtra("IngredientsB", breakfastModel.ingredients)
        intent.putExtra("ImagesB", breakfastModel.images)
        startActivity(intent)
    }

    override fun onLunchRecipeClick(lunchModel: com.masai.foodvisor.model.LunchModel) {
        val intent = Intent(context, IngredientsActivity::class.java)
        intent.putExtra("ArticleNameB", lunchModel.articleName)
        intent.putExtra("CaloriesB", lunchModel.calories)
        intent.putExtra("MinutesB", lunchModel.minutes)
        intent.putExtra("PreparationB", lunchModel.preparation)
        intent.putExtra("IngredientsB", lunchModel.ingredients)
        intent.putExtra("ImagesB", lunchModel.images)
        startActivity(intent)
    }

    override fun onDinnerRecipeClick(dinnerModel: com.masai.foodvisor.model.DinnerModel) {
        val intent = Intent(context, IngredientsActivity::class.java)
        intent.putExtra("ArticleNameB", dinnerModel.articleName)
        intent.putExtra("CaloriesB", dinnerModel.calories)
        intent.putExtra("MinutesB", dinnerModel.minutes)
        intent.putExtra("PreparationB", dinnerModel.preparation)
        intent.putExtra("IngredientsB", dinnerModel.ingredients)
        intent.putExtra("ImagesB", dinnerModel.images)
        startActivity(intent)
    }

    override fun onDrinksRecipeClick(drinksModel: com.masai.foodvisor.model.DrinksModel) {
        val intent = Intent(context, IngredientsActivity::class.java)
        intent.putExtra("ArticleNameB", drinksModel.articleName)
        intent.putExtra("CaloriesB", drinksModel.calories)
        intent.putExtra("MinutesB", drinksModel.minutes)
        intent.putExtra("PreparationB", drinksModel.preparation)
        intent.putExtra("IngredientsB", drinksModel.ingredients)
        intent.putExtra("ImagesB", drinksModel.images)
        startActivity(intent)
    }
}