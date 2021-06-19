package com.shahankbhat.cycleassembly.model

import android.graphics.Color
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableInt

data class BicyclePartModel(
    var partName: String,
    var icon: Int,
    var currentIndex : ObservableInt,
    var size : Float = 1f,
    var tint: Int = Color.parseColor("#000000"),
    var count : ObservableInt = ObservableInt(0)
)