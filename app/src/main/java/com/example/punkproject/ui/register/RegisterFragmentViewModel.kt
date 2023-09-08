package com.example.punkproject.ui.register

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.punkproject.data.locale.UserEntity
import com.example.punkproject.data.repository.RegisterRepository
import com.example.punkproject.data.state.RegisterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterFragmentViewModel @Inject constructor(private val registerRepository: RegisterRepository) :
    ViewModel() {

    private val _registerState: MutableStateFlow<RegisterState> = MutableStateFlow(RegisterState.Idle)
    val registerState: StateFlow<RegisterState> = _registerState

    private val _message: MutableSharedFlow<String> = MutableSharedFlow()
    val message: SharedFlow<String> = _message

    fun register(email: String, password: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    _registerState.value = RegisterState.Loading

                    if(registerRepository.getUserByEmail(email) == RegisterState.Result){
                        val user = UserEntity(email = email, password = password)
                        registerRepository.insert(user)
                        _registerState.value =RegisterState.Result
                        _message.emit("kullanıcı eklendi")
                    }else{
                        _registerState.value = RegisterState.UserAlready
                        _message.emit("kullanıcı zaten var")
                    }

                } else {
                    _message.emit("boş alan bırakmayınız")
                }
            }.onFailure {
                _registerState.value = RegisterState.Error(it)
                _message.emit("kullanıcı eklenirken sorun oluştu")
            }
        }
    }
}