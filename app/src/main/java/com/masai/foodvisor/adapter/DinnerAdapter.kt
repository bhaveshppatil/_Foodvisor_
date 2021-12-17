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

class DinnerAdapter(
    val context: Context,
    var dinnerModelList: MutableList<com.masai.foodvisor.model.DinnerModel>,
    val clicklistener: com.masai.foodvisor.clickListener.Clicklistener
) : RecyclerView.Adapter<com.masai.foodvisor.adapter.DinnerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): com.masai.foodvisor.adapter.DinnerViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.dinner_item_layout, parent, false)
        return com.masai.foodvisor.adapter.DinnerViewHolder(view)
    }

    override fun onBindViewHolder(holder: com.masai.foodvisor.adapter.DinnerViewHolder, position: Int) {
        val dinnerModel = dinnerModelList[position]
        holder.setDinnerData(dinnerModel)

        holder.crdRecipeView.setOnClickListener {
            clicklistener.onDinnerRecipeClick(dinnerModel)
        }
    }

    override fun getItemCount(): Int {
        return dinnerModelList.size
    }

    fun updateDinnerData(dinnerModelList: MutableList<com.masai.foodvisor.model.DinnerModel>) {
        this.dinnerModelList = dinnerModelList
        notifyDataSetChanged()
    }
}

class DinnerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val recipeName: TextView = itemView.findViewById(R.id.tvDinnerRecipe)
    private val ivRecipeView: ImageView = itemView.findViewById(R.id.ivDinnerRecipe)
    val crdRecipeView: CardView = itemView.findViewById(R.id.crd_dinner_view)

    fun setDinnerData(dinnerModel: com.masai.foodvisor.model.DinnerModel) {
        recipeName.text = dinnerModel.articleName
        Glide.with(ivRecipeView).load(dinnerModel.images).into(ivRecipeView)
    }
}