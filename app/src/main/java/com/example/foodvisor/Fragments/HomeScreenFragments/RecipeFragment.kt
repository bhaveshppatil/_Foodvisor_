package com.example.foodvisor.Fragments.HomeScreenFragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodvisor.Adapter.BreakfastAdapter
import com.example.foodvisor.Adapter.DinnerAdapter
import com.example.foodvisor.Adapter.LunchAdapter
import com.example.foodvisor.ClickListener.Clicklistener
import com.example.foodvisor.Model.*
import com.example.foodvisor.R
import com.example.foodvisor.Views.IngredientsActivity
import com.example.foodvisor.Views.PremiumUnlocked
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_recipe.*
import java.io.InputStream


class RecipeFragment : Fragment(R.layout.fragment_recipe), Clicklistener {

    private var breakfastModel = mutableListOf<BreakfastModel>()
    private lateinit var breakfastAdapter: BreakfastAdapter

    private var lunchModelList = mutableListOf<LunchModel>()
    private lateinit var lunchAdapter: LunchAdapter

    private var dinnerModelList = mutableListOf<DinnerModel>()
    private lateinit var dinnerAdapter: DinnerAdapter

    private val runnable = Runnable {
        readBreakfastJson()
        readLunchJson()
        readDinnerJson()
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
        val type = object : TypeToken<Breakfast>() {}.type
        val breakfast: Breakfast = Gson().fromJson(json, type)
        breakfastModel = breakfast.breakfast as MutableList<BreakfastModel>
        updateBreakfastUi()
    }

    private fun buildDinnerFromJson(json: String) {
        val type = object : TypeToken<Dinner>() {}.type
        val dinner: Dinner = Gson().fromJson(json, type)
        dinnerModelList = dinner.dinnerModels as MutableList<DinnerModel>
        updateDinnerUI()
    }

    private fun buildLunchPojoFromJson(json: String) {
        val type = object : TypeToken<Lunch>() {}.type
        val lunch: Lunch = Gson().fromJson(json, type)
        lunchModelList = lunch.lunchModels as MutableList<LunchModel>
        updateLunchUi()
    }

    private fun updateBreakfastUi() {
        activity?.runOnUiThread {
            breakfastAdapter.updateBreakfastData(breakfastModel)
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
        breakfastAdapter = context?.let { BreakfastAdapter(it, breakfastModel, this) }!!
        breakfast_recycler_view.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        breakfast_recycler_view.adapter = breakfastAdapter

        val lunch_recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_lunch)
        lunchAdapter = context?.let { LunchAdapter(it, lunchModelList, this) }!!
        lunch_recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        lunch_recyclerView.adapter = lunchAdapter

        val dinner_recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_Dinner)
        dinnerAdapter = context?.let { DinnerAdapter(it, dinnerModelList, this) }!!
        dinner_recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        dinner_recyclerView.adapter = dinnerAdapter

        startBreakfastBackground()
    }

    override fun onBreakfastRecipeClick(breakfastModel: BreakfastModel) {
        val intent = Intent(context, IngredientsActivity::class.java)
        intent.putExtra("ArticleNameB", breakfastModel.articleName)
        intent.putExtra("CaloriesB", breakfastModel.calories)
        intent.putExtra("MinutesB", breakfastModel.minutes)
        intent.putExtra("PreparationB", breakfastModel.preparation)
        intent.putExtra("IngredientsB", breakfastModel.ingredients)
        intent.putExtra("ImagesB", breakfastModel.images)
        startActivity(intent)
    }

    override fun onLunchRecipeClick(lunchModel: LunchModel) {
        val intent = Intent(context, IngredientsActivity::class.java)
        intent.putExtra("ArticleNameB", lunchModel.articleName)
        intent.putExtra("CaloriesB", lunchModel.calories)
        intent.putExtra("MinutesB", lunchModel.minutes)
        intent.putExtra("PreparationB", lunchModel.preparation)
        intent.putExtra("IngredientsB", lunchModel.ingredients)
        intent.putExtra("ImagesB", lunchModel.images)
        startActivity(intent)
    }

    override fun onDinnerRecipeClick(dinnerModel: DinnerModel) {
        val intent = Intent(context, IngredientsActivity::class.java)
        intent.putExtra("ArticleNameB", dinnerModel.articleName)
        intent.putExtra("CaloriesB", dinnerModel.calories)
        intent.putExtra("MinutesB", dinnerModel.minutes)
        intent.putExtra("PreparationB", dinnerModel.preparation)
        intent.putExtra("IngredientsB", dinnerModel.ingredients)
        intent.putExtra("ImagesB", dinnerModel.images)
        startActivity(intent)    }
}