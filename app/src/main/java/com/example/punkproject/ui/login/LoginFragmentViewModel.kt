package com.example.punkproject.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.punkproject.data.repository.LoginRepository
import com.example.punkproject.data.state.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginFragmentViewModel @Inject constructor(private val loginRepository: LoginRepository) :
    ViewModel() {

    private val _loginState: MutableStateFlow<LoginState> = MutableStateFlow(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            if (email.isNotEmpty() && password.isNotEmpty()) {
                kotlin.runCatching {

                    _loginState.value = LoginState.Loading
                    loginRepository.login(email, password)?.let {
                        _loginState.value = LoginState.Success(it)
                        println("login başarılı")

                    } ?: kotlin.run {

                        _loginState.value = LoginState.UserNotFound
                        println("kullanıcı bulunamadı")

                    }
                }.onFailure {

                    _loginState.value = LoginState.Error(it)
                    println("giriş başarısız ${it.message}")
                }
            }else{
                //alanları boş bırakmayın
            }
        }
    }
}