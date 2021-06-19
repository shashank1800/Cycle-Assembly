package com.shahankbhat.cycleassembly.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.shahankbhat.cycleassembly.R
import com.shahankbhat.cycleassembly.base.BaseFragment
import com.shahankbhat.cycleassembly.databinding.AdapterSelectPartBinding
import com.shahankbhat.cycleassembly.databinding.FragmentPartsSelectionBinding
import com.shahankbhat.cycleassembly.model.BicyclePartModel
import com.shahankbhat.cycleassembly.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.shahankbhat.cycleassembly.BR
import com.shahankbhat.cycleassembly.generic_recycler_adapter.CallBackModel
import com.shahankbhat.cycleassembly.generic_recycler_adapter.RecyclerGenericAdapter

@AndroidEntryPoint
class PartsSelectionFragment : BaseFragment(R.layout.fragment_parts_selection) {

    private lateinit var binding: FragmentPartsSelectionBinding
    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var adapter: RecyclerGenericAdapter<AdapterSelectPartBinding, BicyclePartModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPartsSelectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolBarTitle("Select parts")

        initRecyclerView()
        adapter.submitList(viewModel.cyclePartsList)

        binding.btnContinue.setOnClickListener {
            findNavController().navigate(R.id.nav_assemble_fragment)
        }
    }

    private fun initRecyclerView() {

        val clickListener = ArrayList<CallBackModel<AdapterSelectPartBinding, BicyclePartModel>>()
        clickListener.add(CallBackModel(R.id.root) { model, position, binding ->
            binding.cbSelect.isChecked = !model.isSelected.get()

            if (model.isSelected.get())
                binding.root.setCardBackgroundColor(Color.parseColor("#9BE4D2"))
            else binding.root.setCardBackgroundColor(Color.parseColor("#E9EDEC"))
        })
        adapter = RecyclerGenericAdapter(
            R.layout.adapter_select_part,
            BR.model,
            clickListener
        )

        binding.rvList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvList.adapter = adapter
    }

}