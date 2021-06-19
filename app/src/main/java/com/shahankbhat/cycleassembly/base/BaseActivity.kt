package com.shahankbhat.cycleassembly.base

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity() {

    fun setToolbarTitle(@StringRes toolbarTitleRes: Int, subtitle: String? = "") {
        supportActionBar?.title = getString(toolbarTitleRes)
        supportActionBar?.subtitle = subtitle
    }

    fun showActionBar(){
        supportActionBar?.show()
    }

    fun hideActionBar(){
        supportActionBar?.hide()
    }

    fun hideKeyboard() {
        (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).run {
            val windowToken = currentFocus?.windowToken
            hideSoftInputFromWindow(
                windowToken,
                InputMethodManager.RESULT_UNCHANGED_SHOWN
            )
            currentFocus?.clearFocus()
        }
    }
}