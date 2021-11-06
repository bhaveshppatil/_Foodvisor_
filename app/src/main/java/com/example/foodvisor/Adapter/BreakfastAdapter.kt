package com.example.foodvisor.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodvisor.ClickListener.Clicklistener
import com.example.foodvisor.Model.BreakfastModel
import com.example.foodvisor.R

class BreakfastAdapter(
    val context: Context,
    var breakfastModelModelList: MutableList<BreakfastModel>,
    val clicklistener: Clicklistener
) : RecyclerView.Adapter<BreakfastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreakfastViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.breakfast_item_layout, parent, false)
        return BreakfastViewHolder(view)
    }

    override fun onBindViewHolder(holder: BreakfastViewHolder, position: Int) {
        val breakfastModel = breakfastModelModelList[position]
        holder.setBreakfastData(breakfastModel)

        holder.crdRecipeView.setOnClickListener {
            clicklistener.onBreakfastRecipeClick(breakfastModel)
        }
    }

    override fun getItemCount(): Int {
        return breakfastModelModelList.size
    }

    fun updateBreakfastData(breakfastModelModelList: MutableList<BreakfastModel>) {
        this.breakfastModelModelList = breakfastModelModelList
        notifyDataSetChanged()
    }
}

class BreakfastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val recipeName: TextView = itemView.findViewById(R.id.tvBreakfastName)
    private val ivRecipeView: ImageView = itemView.findViewById(R.id.ivBreakfastRecipe)
    val crdRecipeView: CardView = itemView.findViewById(R.id.crd_breakfast_view)

    fun setBreakfastData(breakfastModel: BreakfastModel) {
        recipeName.text =breakfastModel.articleName
        Glide.with(ivRecipeView).load(breakfastModel.images).into(ivRecipeView)
    }
}