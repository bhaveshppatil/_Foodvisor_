package com.masai.myjournalapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodvisor.Database.adapter.Clicklistener
import com.example.foodvisor.R
import com.masai.myjournalapp.Model.FoodModel


class FoodAdapter(
    val context: Context,
    val foodList: MutableList<FoodModel>,
    val clicklistener: Clicklistener
) : RecyclerView.Adapter<FoodViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val inflater = LayoutInflater.from(context)
        val view1: View = inflater.inflate(R.layout.food_item_layout, parent, false)
        return FoodViewHolder(view1)

    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val foodModel = foodList[position]
        holder.tvFoodName.text = foodModel.name
        holder.tvCaloriesSpear.text = foodModel.calories

        holder.menuBar.setOnClickListener {
            val popupMenu = PopupMenu(context, holder.menuBar)
            popupMenu.inflate(R.menu.menu_list)

            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.remove -> {
                        clicklistener.onFoodRemoveClick(foodModel)
                    }
                }
                false
            })
            popupMenu.show()
        }

    }

    override fun getItemCount(): Int {
        return foodList.size
    }
}

class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val tvFoodName: TextView = itemView.findViewById(R.id.tvFoodName)
    val tvCaloriesSpear: TextView = itemView.findViewById(R.id.tvCaloriesSpear)
    val ivFood: ImageView = itemView.findViewById(R.id.ivFood)
    val menuBar: TextView = itemView.findViewById(R.id.tvMenu)
}
