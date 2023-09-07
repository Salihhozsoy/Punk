package com.example.punkproject.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.punkproject.Extension.showToast
import com.example.punkproject.R
import com.example.punkproject.data.state.LoginState
import com.example.punkproject.databinding.FragmentLoginBinding
import kotlinx.coroutines.launch


class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel:LoginFragmentViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        observeLoginState()
        listeners()

    }

    private fun observeLoginState(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.loginState.collect{
                    when(it){
                        LoginState.Idle->{}
                        LoginState.Loading->{}
                        LoginState.UserNotFound->{
                            requireContext().showToast("kullanıcı bulunamadı")
                        }
                        is LoginState.Success->{
                            findNavController().navigate(R.id.action_loginFragment_to_punkFragment)
                        }
                        is LoginState.Error->{
                            requireContext().showToast("giriş başarısız")
                        }
                    }
                }
            }
        }
    }

    private fun listeners(){
        binding.btnLogin.setOnClickListener {
            viewModel.login(binding.etEmail.text.toString(), binding.etPassword.text.toString())
        }
    }


}