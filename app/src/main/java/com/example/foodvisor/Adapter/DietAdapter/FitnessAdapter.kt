package com.example.foodvisor.Adapter.DietAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodvisor.ClickListener.DietListeners.DietClickListeners
import com.example.foodvisor.Model.DietModel.FitnessModel
import com.example.foodvisor.Model.DietModel.WeightModel
import com.example.foodvisor.R

class FitnessAdapter(
    val context: Context,
    var fitnessModelList: MutableList<FitnessModel>,
    val dietClickListeners: DietClickListeners
) : RecyclerView.Adapter<FitnessViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FitnessViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.fitness_layout, parent, false)
        return FitnessViewHolder(view)
    }

    override fun onBindViewHolder(holder: FitnessViewHolder, position: Int) {
        val fitnessModel = fitnessModelList[position]
        holder.setDinnerData(fitnessModel)

        holder.crd_fitness.setOnClickListener {
            dietClickListeners.onFitnessItemClick(fitnessModel)
        }
    }

    override fun getItemCount(): Int {
        return fitnessModelList.size
    }

    fun updateDietData(fitnessModelList: MutableList<FitnessModel>) {
        this.fitnessModelList = fitnessModelList
        notifyDataSetChanged()
    }
}

class FitnessViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val tvFitnessArticle: TextView = itemView.findViewById(R.id.tvFitnessArticle)
    private val tvFitnessArticleDecs: TextView = itemView.findViewById(R.id.tvFitnessArticleDecs)
    val crd_fitness: CardView = itemView.findViewById(R.id.crd_fitness)

    fun setDinnerData(fitnessModel: FitnessModel) {
        tvFitnessArticle.text = fitnessModel.articleName
        tvFitnessArticleDecs.text = fitnessModel.articleDecs

    }
}