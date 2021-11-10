package com.example.foodvisor.Fragments.HomeScreenFragments

import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodvisor.Adapter.DietAdapter.FitnessAdapter
import com.example.foodvisor.Adapter.DietAdapter.LifestyleAdapter
import com.example.foodvisor.Adapter.DietAdapter.WeightLossAdapter
import com.example.foodvisor.ClickListener.DietListeners.DietClickListeners
import com.example.foodvisor.Model.DietModel.FitnessModel
import com.example.foodvisor.Model.DietModel.LifestyleModel
import com.example.foodvisor.Model.DietModel.WeightModel
import com.example.foodvisor.R

class DietsFragment : Fragment(R.layout.fragment_diets), DietClickListeners {

    private lateinit var weightLossAdapter: WeightLossAdapter
    private var dietModelList = mutableListOf<WeightModel>()

    private lateinit var lifestyleAdapter: LifestyleAdapter
    private var lifestyleModelList = mutableListOf<LifestyleModel>()

    private lateinit var fitnessAdapter: FitnessAdapter
    private var fitnessModelList = mutableListOf<FitnessModel>()
    private lateinit var webView: WebView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val weight_recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_weight)
        val lifestyle_recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_lifeStyle)
        val fitness_recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_fitness)

        webView = view.findViewById<WebView>(R.id.webViewDiet)

        weightLossAdapter = context?.let { WeightLossAdapter(it, dietModelList, this) }!!
        weight_recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        weight_recyclerView.adapter = weightLossAdapter

        lifestyleAdapter = context?.let { LifestyleAdapter(it, lifestyleModelList, this) }!!
        lifestyle_recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        lifestyle_recyclerView.adapter = lifestyleAdapter


        fitnessAdapter = context?.let { FitnessAdapter(it, fitnessModelList, this) }!!
        fitness_recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        fitness_recyclerView.adapter = fitnessAdapter

        buildWeightData()
        buildLifestyleData()
        buildFitnessData()
    }

    private fun buildWeightData() {
        val dietModel = WeightModel(
            "Keto Diet",
            "The two week keto program is based on the ketogenic",
            "https://www.healthline.com/nutrition/ketogenic-diet-101"
        )
        dietModelList.add(dietModel)
        val dietModel1 = WeightModel(
            "Low Crab",
            "In order to quickly lose weight opt for low carbohydrate diet",
            "https://www.healthline.com/nutrition/low-carb-diet-meal-plan-and-menu"
        )
        dietModelList.add(dietModel1)
        val dietModel2 = WeightModel(
            "Detox",
            "Before stating weight loss diet reset yourself",
            "https://www.healthline.com/nutrition/detox-diets-101"
        )
        dietModelList.add(dietModel2)
        val dietModel3 = WeightModel(
            "Stabilization",
            "After weight loss, foods should be reintroduced to balanced diet, watching the body size",
            "https://www.healthline.com/nutrition/maintain-weight-loss#TOC_TITLE_HDR_2"
        )
        dietModelList.add(dietModel3)
    }

    private fun buildLifestyleData() {

        val lifestyleModel3 = LifestyleModel(
            "Mediterranean",
            "The balance fatty acids a healthy lifestyle is the way to healthy",
            "https://www.healthline.com/nutrition/mediterranean-diet-meal-plan"
        )
        lifestyleModelList.add(lifestyleModel3)

        val lifestyleModel4 = LifestyleModel(
            "Gluten Free",
            "You will discover many new and much more nutritious foods ",
            "https://www.healthline.com/nutrition/gluten-free-diet"
        )
        lifestyleModelList.add(lifestyleModel4)


        val lifestyleModel1 = LifestyleModel(
            "Home office",
            "program allows you to discover tips eating well and exercising",
            "https://health.clevelandclinic.org/10-tips-for-eating-healthy-when-youre-working-from-home/"
        )
        lifestyleModelList.add(lifestyleModel1)

        val lifestyleModel = LifestyleModel(
            "Pregnancy",
            "The period of life is very demanding in terms of nutritional quality.",
            "https://www.healthline.com/nutrition/13-foods-to-eat-when-pregnant"
        )
        lifestyleModelList.add(lifestyleModel)

        val lifestyleModel2 = LifestyleModel(
            "Vegetarian",
            "Vegetarian diet involves reducing your lifestyle to keep your health",
            "https://www.healthline.com/nutrition/vegetarian-diet-plan"
        )
        lifestyleModelList.add(lifestyleModel2)

    }

    private fun buildFitnessData() {
        val fitnessModel1 = FitnessModel(
            "Running",
            "This Program is to Adapt your diet to improve your health performance",
            "https://www.healthline.com/nutrition/runners-diet"
        )
        fitnessModelList.add(fitnessModel1)

        val fitnessModel2 = FitnessModel(
            "Tone up",
            "Toning your physique is a phase that should follow bulking up",
            "https://www.muscleandfitness.com/nutrition/healthy-eating/28-days-meal-plan-lean-muscle/"
        )
        fitnessModelList.add(fitnessModel2)

        val fitnessModel3 = FitnessModel(
            "Beach Body",
            "Beach body challenges is a preparation of your body for the sunny das",
            "https://www.verywellfit.com/does-beachbody-work-4158553"
        )
        fitnessModelList.add(fitnessModel3)

        val fitnessModel = FitnessModel(
            "Bulk",
            "Build muscles, get stronger , You have the energy to build muscles",
            "https://www.healthline.com/nutrition/clean-bulk"
        )
        fitnessModelList.add(fitnessModel)

    }

    override fun onWeightItemClick(weightModel: WeightModel) {
        webView.visibility = View.VISIBLE
        startWebView(weightModel.articleLink)
    }

    override fun onLifestyleItemClick(lifestyleModel: LifestyleModel) {
        webView.visibility = View.VISIBLE
        startWebView(lifestyleModel.articleLink)
    }

    override fun onFitnessItemClick(fitnessModel: FitnessModel) {
        webView.visibility = View.VISIBLE
        startWebView(fitnessModel.articleLink)
    }

    private fun startWebView(url: String) {

        val settings = webView.settings
        settings.javaScriptEnabled = true
        webView.settings.setSupportZoom(true)
        WebView.setWebContentsDebuggingEnabled(false)

        webView.scrollBarStyle = View.SCROLLBARS_OUTSIDE_OVERLAY
        webView.settings.builtInZoomControls = true
        webView.settings.useWideViewPort = true
        webView.settings.loadWithOverviewMode = true

        val progressDialog = ProgressDialog(context)
        progressDialog.setMessage("Loading...")
        progressDialog.show()
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }

            override fun onPageFinished(view: WebView, url: String) {
                if (progressDialog.isShowing) {
                    progressDialog.dismiss()
                }
            }

            override fun onReceivedError(
                view: WebView,
                errorCode: Int,
                description: String,
                failingUrl: String
            ) {
                Toast.makeText(context, "Error:$description", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        webView.loadUrl(url)
    }
}