package com.shahankbhat.cycleassembly.model

import android.graphics.Color
import androidx.databinding.ObservableInt

data class BicyclePartModel(
    var partName: String,
    var icon: Int,
    var currentIndex : ObservableInt,
    var size : Float = 1f,
    var tint: Int = Color.parseColor("#000000"),
    var count : ObservableInt = ObservableInt(0),
    var x : Float = -1000f,
    var y : Float = -1000f
){
    fun cloneIt(currentSelectedIndex: ObservableInt): BicyclePartModel {
        return BicyclePartModel(
            this.partName,
            this.icon,
            currentSelectedIndex,
            this.size,
            this.tint,
            this.count,
            this.x,
            this.y
        )
    }
}