package com.example.foodvisor.Adapter.DietAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodvisor.ClickListener.DietListeners.DietClickListeners
import com.example.foodvisor.Model.DietModel.WeightModel
import com.example.foodvisor.R

class WeightLossAdapter(
    val context: Context,
    var weightModelList: MutableList<WeightModel>,
    val dietClickListeners: DietClickListeners
) : RecyclerView.Adapter<DietViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DietViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.weight_loss_layout, parent, false)
        return DietViewHolder(view)
    }

    override fun onBindViewHolder(holder: DietViewHolder, position: Int) {
        val dietModel = weightModelList[position]
        holder.setDinnerData(dietModel)

        holder.crdRecipeView.setOnClickListener {
            dietClickListeners.onWeightItemClick(dietModel)
        }
    }

    override fun getItemCount(): Int {
        return weightModelList.size
    }

    fun updateDietData(weightModelList: MutableList<WeightModel>) {
        this.weightModelList = weightModelList
        notifyDataSetChanged()
    }
}

class DietViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val tvArticleName: TextView = itemView.findViewById(R.id.tvArticleName)
    private val tvArticleDecs: TextView = itemView.findViewById(R.id.tvArticleDecs)
    val crdRecipeView: CardView = itemView.findViewById(R.id.crd_weight_loss)

    fun setDinnerData(weightModel: WeightModel) {
        tvArticleName.text = weightModel.articleName
        tvArticleDecs.text = weightModel.articleDecs

    }
}