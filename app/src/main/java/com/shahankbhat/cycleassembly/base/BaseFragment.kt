package com.shahankbhat.cycleassembly.base

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment(@LayoutRes private val layoutId: Int) : Fragment(layoutId) {


    fun setToolBarTitle(title: String = "") {
        (activity as BaseActivity).supportActionBar?.title = title
    }

}