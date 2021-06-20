package com.shahankbhat.cycleassembly.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment(@LayoutRes private val layoutId: Int) : Fragment(layoutId) {

    var title: String = ""

    fun setToolBarTitle(title: String = "") {
        (activity as BaseActivity).supportActionBar?.title = title
        this.title = title
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(title.isNotEmpty())
            (activity as BaseActivity).supportActionBar?.title = title

    }

}