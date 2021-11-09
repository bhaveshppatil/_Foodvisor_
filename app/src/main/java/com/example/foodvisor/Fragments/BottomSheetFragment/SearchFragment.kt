package com.example.foodvisor.Fragments.BottomSheetFragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.SearchView
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
import com.masai.myjournalapp.Model.FoodModel
import com.masai.myjournalapp.Repository.FoodRepository
import com.masai.myjournalapp.RoomDatabase.FoodDAO
import com.masai.myjournalapp.RoomDatabase.FoodRoomDB
import com.masai.myjournalapp.ViewModel.FoodViewModel
import com.masai.myjournalapp.ViewModel.FoodViewModelFactory
import com.masai.myjournalapp.adapter.FoodAdapter

class SearchFragment : Fragment(R.layout.fragment_search), Clicklistener {

    private val foodList = mutableListOf<FoodModel>()
    lateinit var foodAdapter: FoodAdapter
    private lateinit var foodRoomDB: FoodRoomDB
    private lateinit var foodDAO: FoodDAO
    private lateinit var foodViewModel: FoodViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var textView: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        foodDAO = context?.let { FoodRoomDB.getDatabaseObject(it).getFoodDAO() }!!
        val foodRepository = FoodRepository(foodDAO)
        val foodViewModelFactory = FoodViewModelFactory(foodRepository)
        foodViewModel =
            ViewModelProviders.of(this, foodViewModelFactory).get(FoodViewModel::class.java)

        val searchView = view.findViewById<SearchView>(R.id.searchView)
        recyclerView = view.findViewById<RecyclerView>(R.id.search_recycler_view)
        textView = view.findViewById<TextView>(R.id.tvNoSearch)

        foodAdapter = FoodAdapter(requireContext(), foodList, this)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = foodAdapter
        updateUI(foodList)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                foodViewModel.getSearchFood(query!!).observe(viewLifecycleOwner, Observer {
                    foodList.clear()
                    foodList.addAll(it)
                    updateUI(foodList)
                    foodAdapter.notifyDataSetChanged()
                })
                Toast.makeText(context, query, Toast.LENGTH_SHORT).show()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

    }

    private fun updateUI(foodModelList: List<FoodModel>) {

        if (foodModelList.isEmpty()) {
            recyclerView.visibility = View.GONE
            textView.visibility = View.VISIBLE
        } else {
            textView.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
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
