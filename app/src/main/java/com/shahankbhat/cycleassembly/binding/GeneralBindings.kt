package com.shahankbhat.cycleassembly.binding

import android.graphics.Color
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import com.shahankbhat.cycleassembly.model.BicyclePartModel


object GeneralBindings {

    @JvmStatic
    @BindingAdapter("imageResource")
    fun imageResource(view: ImageView, model: BicyclePartModel) {
        view.setImageResource(model.icon)
    }

    @JvmStatic
    @BindingAdapter(value = ["applyCardDesignWithModel", "applyCardDesignWithAdapterPosition"], requireAll = true)
    fun applyCardDesign(view: CardView, model: BicyclePartModel, position: Int) {
        if (model.currentIndex.get() != position)
            view.cardElevation = 6f
        else view.cardElevation = 20f

        if (model.currentIndex.get() != position)
            view.setCardBackgroundColor(Color.parseColor("#E9EDEC"))
        else view.setCardBackgroundColor(Color.parseColor("#9BE4D2"))
    }

    @JvmStatic
    @BindingAdapter("setCardBackgroundColor")
    fun setCardBackgroundColor(view: CardView, color: Int) {
        view.setCardBackgroundColor(color)
    }

    @JvmStatic
    @BindingAdapter("applyDesigns")
    fun applyDesigns(view: ImageView, model: BicyclePartModel?) {
        model?.icon?.let {
            view.setImageResource(it)
        }
        model?.tint?.let {
            view.setColorFilter(it, android.graphics.PorterDuff.Mode.SRC_IN)
        }

    }

}