package com.shahankbhat.cycleassembly.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.databinding.ObservableInt
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.shahankbhat.cycleassembly.BR
import com.shahankbhat.cycleassembly.R
import com.shahankbhat.cycleassembly.base.BaseFragment
import com.shahankbhat.cycleassembly.databinding.AdapterApplyColorBinding
import com.shahankbhat.cycleassembly.databinding.FragmentAssembleBinding
import com.shahankbhat.cycleassembly.generic_recycler_adapter.CallBackModel
import com.shahankbhat.cycleassembly.generic_recycler_adapter.RecyclerGenericAdapter
import com.shahankbhat.cycleassembly.model.BicyclePartModel
import com.shahankbhat.cycleassembly.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AssembleFragment : BaseFragment(R.layout.fragment_assemble) {

    private lateinit var binding: FragmentAssembleBinding
    private val viewModel: MainViewModel by activityViewModels()

    private var currentSelectedIndex = ObservableInt(0)

    private lateinit var adapter: RecyclerGenericAdapter<AdapterApplyColorBinding, BicyclePartModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAssembleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        val selectedList =
            viewModel.cyclePartsList.filter2 { bicyclePartModel -> bicyclePartModel.isSelected.get() }

        viewModel.selectedList = selectedList
        adapter.submitList(selectedList)

        binding.btnContinue.setOnClickListener {
            findNavController().navigate(R.id.nav_select_color_fragment)
        }
    }

    var xDown = 0f
    var yDown = 0f

    @SuppressLint("NewApi", "ClickableViewAccessibility")
    private fun initRecyclerView() {

        val clickListener = ArrayList<CallBackModel<AdapterApplyColorBinding, BicyclePartModel>>()
        clickListener.add(CallBackModel(R.id.root) { model, position, card ->

            adapter.removeItemAt(position)

            val newImage = ImageView(requireContext())
            val params = RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
            )
//            params.leftMargin = 107
            newImage.id = View.generateViewId()
            newImage.setImageResource(model.icon)
            newImage.x = card.root.x
            newImage.y = card.root.y



            binding.board.addView(newImage, params)

            newImage.setOnTouchListener { v, event ->

                when {
                    MotionEvent.ACTION_DOWN == event.action -> {
                        xDown = event.x
                        yDown = event.y
                    }

                    MotionEvent.ACTION_MOVE == event.action -> {

                        val movedX: Float = event.x
                        val movedY: Float = event.y

                        val distanceX = movedX - xDown
                        val distanceY = movedY - yDown

                        newImage.x = newImage.x + distanceX
                        newImage.y = newImage.y + distanceY

                        xDown = movedX
                        yDown = movedY
                    }
                }

                return@setOnTouchListener true
            }

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
                    it.isSelected,
                )
                arrayList.add(model)
            }
        }
        return arrayList
    }
}