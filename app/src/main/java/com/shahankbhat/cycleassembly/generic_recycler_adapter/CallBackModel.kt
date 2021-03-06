package com.shahankbhat.cycleassembly.generic_recycler_adapter

import androidx.databinding.ViewDataBinding

/**
 * Created by SHASHANK BHAT on 19-Jun-21.
 *
 *
 */

class CallBackModel<BIND_TYPE : ViewDataBinding, MODEL_TYPE>(
    val id: Int,
    val block: (model: MODEL_TYPE, position: Int, binding: BIND_TYPE) -> Unit
)