package com.example.punkproject.ui.punkdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.load
import com.example.punkproject.R
import com.example.punkproject.databinding.FragmentPunkDetailBinding
import com.example.punkproject.ui.punklist.PunkFragment
import com.example.punkproject.ui.punklist.PunkFragment.Companion.PUNKID
import kotlinx.coroutines.launch

class PunkDetailFragment : Fragment(R.layout.fragment_punk_detail) {

    private lateinit var binding: FragmentPunkDetailBinding
    private val viewModel: PunkDetailViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPunkDetailBinding.bind(view)

        observePunkDetailState()

        arguments?.getInt(PUNKID, -1)?.let { punkId ->
            viewModel.getPhotoById(punkId)
        }
    }


    private fun observePunkDetailState() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.punkDetailState.collect {
                    it?.let {
                        binding.tvName.text = it.name
                        binding.ivImage.load(it.image_url)
                        binding.etText.setText(it.description)
                    }

                }
            }
        }
    }
}