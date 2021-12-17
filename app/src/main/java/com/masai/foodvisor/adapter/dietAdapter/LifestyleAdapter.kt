package com.masai.foodvisor.adapter.dietAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.masai.foodvisor.R
import com.masai.foodvisor.model.dietModel.LifestyleModel

class LifestyleAdapter(
    val context: Context,
    var lifestyleModelList: MutableList<LifestyleModel>,
    val dietClickListeners: com.masai.foodvisor.clickListener.dietListeners.DietClickListeners
) : RecyclerView.Adapter<com.masai.foodvisor.adapter.dietAdapter.LifestyleViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): com.masai.foodvisor.adapter.dietAdapter.LifestyleViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.lifestyle_layout, parent, false)
        return com.masai.foodvisor.adapter.dietAdapter.LifestyleViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: com.masai.foodvisor.adapter.dietAdapter.LifestyleViewHolder,
        position: Int
    ) {
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