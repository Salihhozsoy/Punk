package com.example.punkproject.ui.punklist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.punkproject.R
import com.example.punkproject.databinding.FragmentPunkBinding


class PunkFragment : Fragment(R.layout.fragment_punk) {

    private lateinit var binding: FragmentPunkBinding
    private val viewModel:PunkFragmentViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPunkBinding.bind(view)
    }

}