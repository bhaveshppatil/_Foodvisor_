package com.masai.myjournalapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodvisor.R
import com.masai.myjournalapp.Model.FoodModel


class FoodAdapter(
    val context: Context,
    val foodList: MutableList<FoodModel>,
) : RecyclerView.Adapter<FoodViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val inflater = LayoutInflater.from(context)
        val view1: View = inflater.inflate(R.layout.food_item_layout, parent, false)
        return FoodViewHolder(view1)

    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {

        val foodModel = foodList[position]
        holder.title.text = foodModel.name
        holder.decs.text = foodModel.decs
        holder.setFoodData(foodModel)
    }

    override fun getItemCount(): Int {
        return foodList.size
    }
}

class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val title: TextView = itemView.findViewById(R.id.tvFoodName)
    val decs: TextView = itemView.findViewById(R.id.tvFoodDecs)
    val ivFood: ImageView = itemView.findViewById(R.id.ivFood)

    fun setFoodData(foodModel: FoodModel) {
        Glide.with(ivFood).load(foodModel.link).into(ivFood)
    }
}
