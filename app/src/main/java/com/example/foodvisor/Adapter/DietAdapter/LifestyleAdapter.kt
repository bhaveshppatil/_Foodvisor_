package com.example.foodvisor.Adapter.DietAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodvisor.ClickListener.DietListeners.DietClickListeners
import com.example.foodvisor.Model.DietModel.LifestyleModel
import com.example.foodvisor.Model.DietModel.WeightModel
import com.example.foodvisor.R

class LifestyleAdapter(
    val context: Context,
    var lifestyleModelList: MutableList<LifestyleModel>,
    val dietClickListeners: DietClickListeners
) : RecyclerView.Adapter<LifestyleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LifestyleViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.lifestyle_layout, parent, false)
        return LifestyleViewHolder(view)
    }

    override fun onBindViewHolder(holder: LifestyleViewHolder, position: Int) {
        val lifestyleModel = lifestyleModelList[position]
        holder.setDinnerData(lifestyleModel)

        holder.crd_lifestyle.setOnClickListener {
            dietClickListeners.onLifestyleItemClick(lifestyleModel)
        }
    }

    override fun getItemCount(): Int {
        return lifestyleModelList.size
    }

    fun updateDietData(lifestyleModelList: MutableList<LifestyleModel>) {
        this.lifestyleModelList = lifestyleModelList
        notifyDataSetChanged()
    }
}

class LifestyleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val tvLifestyleArticle: TextView = itemView.findViewById(R.id.tvLifestyleArticle)
    private val tvLifestyleDecs: TextView = itemView.findViewById(R.id.tvLifestyleDecs)
    val crd_lifestyle: CardView = itemView.findViewById(R.id.crd_lifestyle)

    fun setDinnerData(lifestyleModel: LifestyleModel) {
        tvLifestyleArticle.text = lifestyleModel.articleName
        tvLifestyleDecs.text = lifestyleModel.articleDecs

    }
}