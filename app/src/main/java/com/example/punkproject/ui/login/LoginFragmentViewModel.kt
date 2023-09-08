package com.example.punkproject.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.punkproject.data.repository.LoginRepository
import com.example.punkproject.data.state.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginFragmentViewModel @Inject constructor(private val loginRepository: LoginRepository) :
    ViewModel() {

    private val _loginState: MutableStateFlow<LoginState> = MutableStateFlow(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    private val _message: MutableSharedFlow<String> = MutableSharedFlow()
    val message: SharedFlow<String> = _message

    fun login(email: String, password: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    _loginState.value = LoginState.Loading
                    if (loginRepository.login(email, password) == LoginState.UserNotFound) {
                        _message.emit("kullanıcı bulunamadı")
                    } else {
                        _loginState.value = LoginState.Success
                        _message.emit("giriş başarılı")
                    }
                } else {
                    _message.emit("Boş alan bırakma")
                }
            }.onFailure {
                _loginState.value = LoginState.Error(it)
            }
        }
    }
}