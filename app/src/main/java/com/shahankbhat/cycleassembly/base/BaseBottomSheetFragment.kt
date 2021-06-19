package com.shahankbhat.cycleassembly.base

import android.content.Context
import android.content.SharedPreferences
import android.view.inputmethod.InputMethodManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import javax.inject.Inject

abstract class BaseBottomSheetFragment : BottomSheetDialogFragment() {

    fun hideKeyboard() {
        (requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).run {
            val windowToken = view?.rootView?.windowToken
            hideSoftInputFromWindow(
                windowToken,
                InputMethodManager.RESULT_UNCHANGED_SHOWN
            )
            view?.rootView?.clearFocus()
        }
    }
}