package com.example.punkproject.ui.register

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.punkproject.data.locale.UserEntity
import com.example.punkproject.data.repository.RegisterRepository
import com.example.punkproject.data.state.RegisterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterFragmentViewModel @Inject constructor(private val registerRepository: RegisterRepository) :
    ViewModel() {

    private val _registerState: MutableStateFlow<RegisterState> =
        MutableStateFlow(RegisterState.Idle)
    val registerState: StateFlow<RegisterState> = _registerState

    fun register(email: String, password: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    _registerState.value = RegisterState.Loading
                    _registerState.value = registerRepository.getUserByEmail(email)
                    val user = UserEntity(email = email, password = password)
                    registerRepository.insert(user)
                } else {
                    // boş alan
                }
            }.onFailure {
                _registerState.value = RegisterState.Error(it)
            }
        }
    }
}