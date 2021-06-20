package com.shahankbhat.cycleassembly.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.shahankbhat.cycleassembly.R
import com.shahankbhat.cycleassembly.base.BaseFragment
import com.shahankbhat.cycleassembly.databinding.FragmentProductViewBinding
import com.shahankbhat.cycleassembly.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductViewFragment : BaseFragment(R.layout.fragment_product_view) {

    private lateinit var binding: FragmentProductViewBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolBarTitle("Final View")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {

            delay(200)

            viewModel.selectedList.forEach { model ->
                val newImage = ImageView(requireContext())

                val size = binding.board.width * model.size
                val params = RelativeLayout.LayoutParams(size.toInt(), size.toInt())

                newImage.setImageResource(model.icon)
                newImage.setColorFilter(model.tint, android.graphics.PorterDuff.Mode.SRC_IN)
                newImage.x = model.x
                newImage.y = model.y

                binding.board.addView(newImage, params)
            }
        }



    }

}