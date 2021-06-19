package com.shahankbhat.cycleassembly.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ObservableInt
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.shahankbhat.cycleassembly.BR
import com.shahankbhat.cycleassembly.R
import com.shahankbhat.cycleassembly.base.BaseFragment
import com.shahankbhat.cycleassembly.bottom_sheet.SelectColorBottomSheetFragment
import com.shahankbhat.cycleassembly.databinding.AdapterApplyColorBinding
import com.shahankbhat.cycleassembly.databinding.FragmentSelectColorBinding
import com.shahankbhat.cycleassembly.generic_recycler_adapter.CallBackModel
import com.shahankbhat.cycleassembly.generic_recycler_adapter.RecyclerGenericAdapter
import com.shahankbhat.cycleassembly.model.BicyclePartModel
import com.shahankbhat.cycleassembly.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectColorFragment : BaseFragment(R.layout.fragment_select_color) {
    private lateinit var binding: FragmentSelectColorBinding
    private val viewModel: MainViewModel by activityViewModels()

    var currentSelectedIndex = ObservableInt(0)

    private lateinit var adapter: RecyclerGenericAdapter<AdapterApplyColorBinding, BicyclePartModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSelectColorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolBarTitle("Select colors")

        initRecyclerView()

        viewModel.selectedList =
            viewModel.cyclePartsList.filter2 { bicyclePartModel -> bicyclePartModel.isSelected.get() }


        adapter.submitList(viewModel.selectedList)

        binding.model = viewModel.selectedList[currentSelectedIndex.get()]

        binding.btnSelectColor.setOnClickListener {
            val dialog =
                SelectColorBottomSheetFragment(object : SelectColorBottomSheetFragment.Listener {
                    override fun onSelectColor(color: Int) {
                        viewModel.selectedList[currentSelectedIndex.get()].tint = color
                        binding.model = viewModel.selectedList[currentSelectedIndex.get()]
                    }
                })
            dialog.show(parentFragmentManager, dialog.tag)
        }

    }

    private fun initRecyclerView() {

        val clickListener = ArrayList<CallBackModel<AdapterApplyColorBinding, BicyclePartModel>>()
        clickListener.add(CallBackModel(R.id.root) { model, position, card ->
            currentSelectedIndex.set(position)
            adapter.notifyDataSetChanged()

            binding.model = viewModel.selectedList[currentSelectedIndex.get()]
        })
        adapter = RecyclerGenericAdapter(
            R.layout.adapter_apply_color,
            BR.model,
            clickListener
        )

        binding.rvList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvList.adapter = adapter
    }

    private fun ArrayList<BicyclePartModel>.filter2(function: (BicyclePartModel) -> Boolean): ArrayList<BicyclePartModel> {
        val arrayList = ArrayList<BicyclePartModel>()

        this.forEach {
            if (function(it)) {
                val model = BicyclePartModel(
                    it.partName,
                    it.icon,
                    currentSelectedIndex,
                    it.size,
                    it.tint,
                    it.isSelected
                )
                arrayList.add(model)
            }
        }
        return arrayList
    }
}



