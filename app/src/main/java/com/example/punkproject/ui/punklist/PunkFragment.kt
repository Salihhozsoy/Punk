package com.example.punkproject.ui.punklist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.punkproject.R
import com.example.punkproject.data.model.ResponseItem
import com.example.punkproject.data.state.PhotoListState
import com.example.punkproject.databinding.FragmentPunkBinding
import kotlinx.coroutines.launch


class PunkFragment : Fragment(R.layout.fragment_punk) {

    private lateinit var binding: FragmentPunkBinding
    private val viewModel:PunkFragmentViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPunkBinding.bind(view)

        observePhotoListState()
        viewModel.getAllPhotos("beers")
    }
    private fun observePhotoListState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.photoListState.collect {
                    when (it) {
                        PhotoListState.Idle -> {}
                        PhotoListState.Loading -> {}
                        PhotoListState.Empty -> {}
                        is PhotoListState.Result -> {
                         binding.rvPunks.adapter=PhotoAdapter(requireContext(),it.items,this@PunkFragment::onClick)
                            println("geldi ${it.items}")
                        }
                        is PhotoListState.Error -> {}
                    }
                }
            }
        }
    }

    private fun onClick(responseItem: ResponseItem) {}

}