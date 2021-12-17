package com.masai.foodvisor.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.masai.foodvisor.R

class LunchAdapter(
    val context: Context,
    var lunchModelModelList: MutableList<com.masai.foodvisor.model.LunchModel>,
    val clicklistener: com.masai.foodvisor.clickListener.Clicklistener
) : RecyclerView.Adapter<com.masai.foodvisor.adapter.LunchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): com.masai.foodvisor.adapter.LunchViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.lunch_item_layout, parent, false)
        return com.masai.foodvisor.adapter.LunchViewHolder(view)
    }

    override fun onBindViewHolder(holder: com.masai.foodvisor.adapter.LunchViewHolder, position: Int) {
        val lunchModelList = lunchModelModelList[position]
        holder.setLunchData(lunchModelList)

        holder.crdRecipeView.setOnClickListener {
            clicklistener.onLunchRecipeClick(lunchModelList)
        }
    }

    override fun getItemCount(): Int {
        return lunchModelModelList.size
    }

    fun updateLunchData(lunchModelModelList: MutableList<com.masai.foodvisor.model.LunchModel>) {
        this.lunchModelModelList = lunchModelModelList
        notifyDataSetChanged()
    }
}

class LunchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val recipeName: TextView = itemView.findViewById(R.id.tvLunchRecipe)
    private val ivRecipeView: ImageView = itemView.findViewById(R.id.ivLunchRecipe)
    val crdRecipeView: CardView = itemView.findViewById(R.id.crd_lunch_view)


    fun setLunchData(lunchModel: com.masai.foodvisor.model.LunchModel) {
        recipeName.text = lunchModel.articleName
        Glide.with(ivRecipeView).load(lunchModel.images).into(ivRecipeView)
    }
}