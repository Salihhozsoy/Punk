package com.example.punkproject.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.punkproject.Extension.showToast
import com.example.punkproject.R
import com.example.punkproject.data.state.RegisterState
import com.example.punkproject.databinding.FragmentRegisterBinding
import kotlinx.coroutines.launch

class RegisterFragment : Fragment(R.layout.fragment_register) {

    private lateinit var binding: FragmentRegisterBinding
    private val viewModel:RegisterFragmentViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentRegisterBinding.bind(view)

        observeMessage()
        observeRegisterState()
        listeners()

    }
   private fun observeRegisterState(){
        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.registerState.collect{
                    when(it){
                        RegisterState.Idle->{}
                        RegisterState.UserAlready->{}
                        RegisterState.Loading->{}
                        is RegisterState.Result->{
                            findNavController().navigate(R.id.action_registerFragment_to_punkFragment)
                        }
                        is RegisterState.Error->{}
                    }
                }
            }
        }
    }
    private fun observeMessage(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.message.collect{
                    requireContext().showToast(it)
                }
            }
        }
    }
    private fun listeners(){
        binding.btnRegisterSignUp.setOnClickListener {
            viewModel.register(binding.etRegisterEmail.text.toString().trim(),binding.etRegisterPassword.text.toString().trim(),binding.etConfirmPassword.text.toString().trim())
        }
    }
}