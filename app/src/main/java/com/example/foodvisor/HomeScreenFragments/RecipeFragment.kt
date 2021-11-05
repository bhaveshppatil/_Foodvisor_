package com.example.foodvisor.HomeScreenFragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodvisor.Adapter.BreakfastAdapter
import com.example.foodvisor.Adapter.LunchAdapter
import com.example.foodvisor.ClickListener.Clicklistener
import com.example.foodvisor.Model.Breakfast
import com.example.foodvisor.Model.BreakfastModel
import com.example.foodvisor.Model.Lunch
import com.example.foodvisor.Model.LunchModel
import com.example.foodvisor.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStream


class RecipeFragment : Fragment(R.layout.fragment_recipe), Clicklistener {

    private var breakfastModel = mutableListOf<BreakfastModel>()
    private lateinit var breakfastAdapter: BreakfastAdapter

    private var lunchModelList = mutableListOf<LunchModel>()
    private lateinit var lunchAdapter: LunchAdapter

    private val runnable = Runnable {
        readBreakfastJson()
        readLunchJson()
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

        startBreakfastBackground()
    }

    override fun onBreakfastRecipeClick(breakfastModel: BreakfastModel) {
        Toast.makeText(context, "Breakfast item clicked", Toast.LENGTH_SHORT).show()
    }

    override fun onLunchRecipeClick(lunchModel: LunchModel) {
        Toast.makeText(context, "Lunch item clicked", Toast.LENGTH_SHORT).show()
    }
}