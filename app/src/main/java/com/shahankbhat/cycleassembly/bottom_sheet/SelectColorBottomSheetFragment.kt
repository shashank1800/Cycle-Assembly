package com.shahankbhat.cycleassembly.bottom_sheet

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.shahankbhat.cycleassembly.BR
import com.shahankbhat.cycleassembly.R
import com.shahankbhat.cycleassembly.base.BaseBottomSheetFragment
import com.shahankbhat.cycleassembly.databinding.AdapterColorBinding
import com.shahankbhat.cycleassembly.databinding.FragmentSelectColorBottomSheetBinding
import com.shahankbhat.cycleassembly.generic_recycler_adapter.CallBackModel
import com.shahankbhat.cycleassembly.generic_recycler_adapter.RecyclerGenericAdapter


class SelectColorBottomSheetFragment(val listener : Listener) : BaseBottomSheetFragment() {

    private lateinit var binding: FragmentSelectColorBottomSheetBinding
    private lateinit var adapter : RecyclerGenericAdapter<AdapterColorBinding, Int>

    private val colorList = arrayListOf<Int>()
    interface Listener {
        fun onSelectColor(color: Int)
    }

    init {
        colorList.add(Color.parseColor("#5C95EA"))
        colorList.add(Color.parseColor("#BD3D8A"))
        colorList.add(Color.parseColor("#983D36"))
        colorList.add(Color.parseColor("#C5B530"))
        colorList.add(Color.parseColor("#46A14A"))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSelectColorBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val clickListener = ArrayList<CallBackModel<AdapterColorBinding, Int>>()
        clickListener.add(CallBackModel(R.id.root) { model, position, binding ->
            listener.onSelectColor(model)
            dismiss()
        })
        adapter = RecyclerGenericAdapter(
            R.layout.adapter_color,
            BR.model,
            clickListener
        )

        binding.rvList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvList.adapter = adapter

        adapter.submitList(colorList)
    }

}