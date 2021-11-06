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
import com.example.foodvisor.Model.DrinksModel
import com.example.foodvisor.R

class DrinksAdapter(
    val context: Context,
    var drinksModelList: MutableList<DrinksModel>,
    val clicklistener: Clicklistener
) : RecyclerView.Adapter<DrinksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinksViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.drinks_item_layout, parent, false)
        return DrinksViewHolder(view)
    }

    override fun onBindViewHolder(holder: DrinksViewHolder, position: Int) {
        val drinksModelList = drinksModelList[position]
        holder.setLunchData(drinksModelList)

        holder.crdRecipeView.setOnClickListener {
            clicklistener.onDrinksRecipeClick(drinksModelList)
        }
    }

    override fun getItemCount(): Int {
        return drinksModelList.size
    }

    fun updateDrinksData(drinksModelList: MutableList<DrinksModel>) {
        this.drinksModelList = drinksModelList
        notifyDataSetChanged()
    }
}

class DrinksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val recipeName: TextView = itemView.findViewById(R.id.tvDrinksRecipe)
    private val ivRecipeView: ImageView = itemView.findViewById(R.id.ivDrinksRecipe)
    val crdRecipeView: CardView = itemView.findViewById(R.id.crd_drinks_view)

    fun setLunchData(drinksModel: DrinksModel) {
        recipeName.text = drinksModel.articleName
        Glide.with(ivRecipeView).load(drinksModel.images).into(ivRecipeView)
    }
}