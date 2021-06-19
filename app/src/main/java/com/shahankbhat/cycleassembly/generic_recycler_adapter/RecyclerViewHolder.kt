package com.shahankbhat.cycleassembly.generic_recycler_adapter

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.shahankbhat.cycleassembly.BR

/**
 * Created by SHASHANK BHAT on 19-Jun-21.
 *
 *
 */

class RecyclerViewHolder<BIND_TYPE : ViewDataBinding>(var binding: BIND_TYPE) :
    RecyclerView.ViewHolder(binding.root) {
    fun <MODEL_TYPE> bindTo(model: MODEL_TYPE, variableId: Int) {
        binding.setVariable(variableId, model)
        binding.setVariable(BR.adapterPosition, adapterPosition)
    }

    fun <MODEL_TYPE> bindClickListener(model: MODEL_TYPE, callbacks: ArrayList<CallBackModel<BIND_TYPE, MODEL_TYPE>>?){

        callbacks?.forEach { callback ->
            binding.root.findViewById<View>(callback.id).setOnClickListener {
                callback.block(model, adapterPosition, binding)
            }
        }

    }

    fun <MODEL_TYPE> bindOnLongClickListener(model: MODEL_TYPE, callbacks: ArrayList<CallBackModel<BIND_TYPE, MODEL_TYPE>>?){

        callbacks?.forEach { callback ->
            binding.root.findViewById<View>(callback.id).setOnLongClickListener {
                callback.block(model, adapterPosition, binding)
                return@setOnLongClickListener true
            }
        }

    }
}