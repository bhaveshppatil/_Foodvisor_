package com.example.foodvisor.Fragments.BottomSheetFragment

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

class PhotoFragment : Fragment(R.layout.fragment_photo) {

    private val foodList = mutableListOf<FoodModel>()
    lateinit var foodAdapter: FoodAdapter
    private lateinit var foodRoomDB: FoodRoomDB
    private lateinit var foodDAO: FoodDAO
    private lateinit var foodViewModel: FoodViewModel

    private lateinit var recyclerview: RecyclerView
//    private lateinit var cardView: CardView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        foodDAO = context?.let { FoodRoomDB.getDatabaseObject(it).getFoodDAO() }!!
        val foodRepository = FoodRepository(foodDAO)
        val foodViewModelFactory = FoodViewModelFactory(foodRepository)
        foodViewModel =
            ViewModelProviders.of(this, foodViewModelFactory).get(FoodViewModel::class.java)

        val btnFab = view.findViewById<FloatingActionButton>(R.id.btnFab)
        recyclerview = view.findViewById<RecyclerView>(R.id.food_recycler_view)
//        cardView = view.findViewById<CardView>(R.id.crdFood)

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

                val foodTitle = dialog.etFoodName.text.toString()
                val foodDecs = dialog.etDecs.text.toString()
                val imageLink = dialog.etImageLink.text.toString()

                val routineModel = FoodModel(foodTitle, foodDecs, imageLink)
                foodViewModel.addFoodData(routineModel)
                dialog.dismiss()
            }
            dialog.show()
        }

        foodAdapter = FoodAdapter(requireContext(), foodList)
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
//            cardView.visibility = View.VISIBLE
        } else {
//            cardView.visibility = View.GONE
            recyclerview.visibility = View.VISIBLE
        }
    }
}