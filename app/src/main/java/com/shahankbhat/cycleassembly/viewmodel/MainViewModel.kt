package com.shahankbhat.cycleassembly.viewmodel

import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import com.shahankbhat.cycleassembly.R
import com.shahankbhat.cycleassembly.model.BicyclePartModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    var cyclePartsList = ArrayList<BicyclePartModel>()
    var selectedList = ArrayList<BicyclePartModel>()

    init {
        val currentIndex = ObservableInt(0)
        cyclePartsList.add(
            BicyclePartModel(
                "Frame",
                R.drawable.part_body_frame,
                currentIndex,
                0.6f
            )
        )

        cyclePartsList.add(
            BicyclePartModel(
                "Handle",
                R.drawable.part_handle,
                currentIndex,
                0.2f
            )
        )

        cyclePartsList.add(
            BicyclePartModel(
                "Inner tube",
                R.drawable.part_inner_tube,
                currentIndex,
                0.2f
            )
        )


        cyclePartsList.add(
            BicyclePartModel(
                "Tyre",
                R.drawable.part_tyre,
                currentIndex,
                0.4f
            )
        )

        cyclePartsList.add(
            BicyclePartModel(
                "Seat",
                R.drawable.part_seat,
                currentIndex,
                0.2f
            )
        )
    }
}