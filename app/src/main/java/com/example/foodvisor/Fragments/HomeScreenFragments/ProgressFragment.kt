package com.example.foodvisor.Fragments.HomeScreenFragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.anychart.APIlib
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.enums.Anchor
import com.anychart.enums.HoverMode
import com.anychart.enums.Position
import com.anychart.enums.TooltipPositionMode
import com.bumptech.glide.Glide
import com.example.foodvisor.R
import com.example.foodvisor.Views.LoginActivity
import com.example.foodvisor.Views.PremiumUnlocked
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_progress.*
import java.util.*


class ProgressFragment : Fragment(R.layout.fragment_progress) {

    private var mAuth: FirebaseAuth? = null
    private lateinit var imageView: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mAuth = FirebaseAuth.getInstance()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        // Inflate the layout for this fragment
        val user = mAuth!!.currentUser
        tvUserName.text = user!!.displayName
        Glide.with(imageView).load(user.photoUrl).into(imageView)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageView = view.findViewById(R.id.imageView1)

        btnPremium.setOnClickListener {
            val intent = Intent(context, PremiumUnlocked::class.java)
            startActivity(intent)
        }

        btnLogout.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.apply {
                setTitle("Are you sure you want to Logout??")
                setPositiveButton("Yes") { _, _ ->
                    mAuth?.signOut()
                    val intent = Intent(context, LoginActivity::class.java)
                    startActivity(intent)
                }
                setNegativeButton("No") { _, _ -> }
                create()
                show()
            }
        }

        val anyChartView = view.findViewById<AnyChartView>(R.id.any_chart_view_random)
        APIlib.getInstance().setActiveAnyChartView(anyChartView)

        val pie = AnyChart.pie()
        val data: MutableList<DataEntry> = ArrayList()
        data.add(ValueDataEntry("fats", 9))
        data.add(ValueDataEntry("proteins", 36))
        data.add(ValueDataEntry("carbs", 43))
        data.add(ValueDataEntry("fibers", 11))
        pie.data(data)
        pie.title("My consumption")
        anyChartView.setChart(pie)

        val anyChartView1 = view.findViewById<AnyChartView>(R.id.any_chart_view1_random)
        APIlib.getInstance().setActiveAnyChartView(anyChartView1)

        val pie1 = AnyChart.bar()

        val data1: MutableList<DataEntry> = ArrayList()
        data1.add(ValueDataEntry("fats", 6371664))
        data1.add(ValueDataEntry("proteins", 789622))
        data1.add(ValueDataEntry("carbs", 7216301))
        data1.add(ValueDataEntry("fibers", 1486621))
        pie1.data(data1)
        pie1.title("Macronutrient average(g)")
        anyChartView1.setChart(pie1)

        val anyChartView2 = view.findViewById<AnyChartView>(R.id.any_chart_view2_random)
        APIlib.getInstance().setActiveAnyChartView(anyChartView2)

        val cartesian = AnyChart.column()
        val data2: MutableList<DataEntry> = ArrayList()
        data2.add(ValueDataEntry("fats", 400))
        data2.add(ValueDataEntry("proteins", 800))
        data2.add(ValueDataEntry("carbs", 1200))
        data2.add(ValueDataEntry("fibers", 1600))

        val column = cartesian.column(data2)
        column.tooltip()
            .titleFormat("{%X}")
            .position(Position.CENTER_BOTTOM)
            .anchor(Anchor.CENTER_BOTTOM)
            .offsetX(0.0)
            .offsetY(5.0)
        cartesian.title("Daily calories goal")
        cartesian.yScale().minimum(0.0)
        cartesian.tooltip().positionMode(TooltipPositionMode.POINT)
        cartesian.interactivity().hoverMode(HoverMode.BY_X)
        cartesian.xAxis(0).title("Goal")
        cartesian.yAxis(0).title("Calories")
        anyChartView2.setChart(cartesian)

    }
}