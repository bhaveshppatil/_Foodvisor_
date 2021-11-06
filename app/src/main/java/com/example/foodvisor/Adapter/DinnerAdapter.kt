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
import com.example.foodvisor.Model.DinnerModel
import com.example.foodvisor.R

class DinnerAdapter(
    val context: Context,
    var dinnerModelList: MutableList<DinnerModel>,
    val clicklistener: Clicklistener
) : RecyclerView.Adapter<DinnerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DinnerViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.dinner_item_layout, parent, false)
        return DinnerViewHolder(view)
    }

    override fun onBindViewHolder(holder: DinnerViewHolder, position: Int) {
        val dinnerModel = dinnerModelList[position]
        holder.setDinnerData(dinnerModel)

        holder.crdRecipeView.setOnClickListener {
            clicklistener.onDinnerRecipeClick(dinnerModel)
        }
    }

    override fun getItemCount(): Int {
        return dinnerModelList.size
    }

    fun updateDinnerData(dinnerModelList: MutableList<DinnerModel>) {
        this.dinnerModelList = dinnerModelList
        notifyDataSetChanged()
    }
}

class DinnerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val recipeName: TextView = itemView.findViewById(R.id.tvDinnerRecipe)
    private val ivRecipeView: ImageView = itemView.findViewById(R.id.ivDinnerRecipe)
    val crdRecipeView: CardView = itemView.findViewById(R.id.crd_dinner_view)

    fun setDinnerData(dinnerModel: DinnerModel) {
        recipeName.text = dinnerModel.articleName
        Glide.with(ivRecipeView).load(dinnerModel.images).into(ivRecipeView)
    }
}