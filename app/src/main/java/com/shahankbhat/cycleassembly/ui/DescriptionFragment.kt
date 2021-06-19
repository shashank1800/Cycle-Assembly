package com.shahankbhat.cycleassembly.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.shahankbhat.cycleassembly.R
import com.shahankbhat.cycleassembly.base.BaseFragment
import com.shahankbhat.cycleassembly.databinding.FragmentDiscriptionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DescriptionFragment : BaseFragment(R.layout.fragment_discription) {
    private lateinit var binding: FragmentDiscriptionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDiscriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnStart.setOnClickListener {
            findNavController().navigate(R.id.nav_parts_selection_fragment)
        }
    }

}