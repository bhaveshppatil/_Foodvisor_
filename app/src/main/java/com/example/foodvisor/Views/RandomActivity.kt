package com.example.foodvisor.Views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.foodvisor.R
import com.anychart.AnyChartView
import com.anychart.APIlib
import com.anychart.charts.Pie
import com.anychart.AnyChart
import com.anychart.chart.common.listener.ListenersInterface
import android.widget.Toast
import com.anychart.chart.common.dataentry.DataEntry
import java.util.ArrayList
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.chart.common.listener.Event
import com.anychart.enums.Anchor
import com.anychart.enums.TooltipPositionMode
import com.anychart.enums.HoverMode
import com.anychart.enums.Position

class RandomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random)
    }
}