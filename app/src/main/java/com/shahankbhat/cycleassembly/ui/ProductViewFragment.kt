package com.shahankbhat.cycleassembly.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.shahankbhat.cycleassembly.R
import com.shahankbhat.cycleassembly.base.BaseFragment
import com.shahankbhat.cycleassembly.databinding.FragmentProductViewBinding
import com.shahankbhat.cycleassembly.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductViewFragment : BaseFragment(R.layout.fragment_product_view) {

    private lateinit var binding: FragmentProductViewBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductViewBinding.inflate(inflater, container, false)
        return binding.root
    }

}