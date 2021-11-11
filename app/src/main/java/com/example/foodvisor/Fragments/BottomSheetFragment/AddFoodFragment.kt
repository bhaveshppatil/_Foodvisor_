package com.example.foodvisor.Fragments.BottomSheetFragment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodvisor.Database.adapter.Clicklistener
import com.example.foodvisor.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.masai.myjournalapp.Model.FoodModel
import com.masai.myjournalapp.Repository.FoodRepository
import com.masai.myjournalapp.RoomDatabase.FoodDAO
import com.masai.myjournalapp.RoomDatabase.FoodRoomDB
import com.masai.myjournalapp.ViewModel.FoodViewModel
import com.masai.myjournalapp.ViewModel.FoodViewModelFactory
import com.masai.myjournalapp.adapter.FoodAdapter
import kotlinx.android.synthetic.main.add_new_food.*

class AddFoodFragment : Fragment(R.layout.fragment_photo), Clicklistener {

    private val foodList = mutableListOf<FoodModel>()
    lateinit var foodAdapter: FoodAdapter
    private lateinit var foodDAO: FoodDAO
    private lateinit var foodViewModel: FoodViewModel
    private lateinit var foodModel: FoodModel
    private lateinit var recyclerview: RecyclerView
    private lateinit var textView: TextView

    //    private lateinit var cardView: CardView
    private var data: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        foodDAO = context?.let { FoodRoomDB.getDatabaseObject(it).getFoodDAO() }!!
        val foodRepository = FoodRepository(foodDAO)
        val foodViewModelFactory = FoodViewModelFactory(foodRepository)
        foodViewModel =
            ViewModelProviders.of(this, foodViewModelFactory).get(FoodViewModel::class.java)

        val btnFab = view.findViewById<FloatingActionButton>(R.id.btnFab)
        recyclerview = view.findViewById<RecyclerView>(R.id.food_recycler_view)

        textView = view.findViewById<TextView>(R.id.tvNoFood)

        btnFab.setOnClickListener {
            val dialog = Dialog(requireContext())
            dialog.setContentView(R.layout.add_new_food)

            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            dialog.ivCancel.setOnClickListener {
                dialog.dismiss()
            }

            dialog.btnAddRoutine.setOnClickListener {
                data++
                val foodTitle = dialog.etFoodName.text.toString()

                foodModel = if (data % 2 == 0) {
                    FoodModel(foodTitle, "35 cal, 5 spear(64g", 35)
                } else {
                    FoodModel(foodTitle, "63 cal, 7 spear(82g", 65)
                }

                foodViewModel.addFoodData(foodModel)

                dialog.dismiss()
            }
            dialog.show()
        }

        foodAdapter = FoodAdapter(requireContext(), foodList, this)
        recyclerview.layoutManager = LinearLayoutManager(context)
        recyclerview.adapter = foodAdapter

        foodViewModel.getFood().observe(viewLifecycleOwner, Observer {
            foodList.clear()
            foodList.addAll(it)
            updateUI(foodList)
            foodAdapter.notifyDataSetChanged()
        })

    }

    private fun updateUI(foodModelList: List<FoodModel>) {

        if (foodModelList.isEmpty()) {
            recyclerview.visibility = View.GONE
            textView.visibility = View.VISIBLE
        } else {
            textView.visibility = View.GONE
            recyclerview.visibility = View.VISIBLE
        }
    }

    override fun onFoodRemoveClick(foodModel: FoodModel) {
        val builder = AlertDialog.Builder(context)
        builder.apply {
            setTitle("Do you want to remove this food??")
            setPositiveButton("Yes") { _, _ ->
                foodViewModel.removeFood(foodModel)
                showToast("Food deleted successfully")
            }
            setNegativeButton("No") { _, _ -> }
            create()
            show()
        }
    }

    fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}