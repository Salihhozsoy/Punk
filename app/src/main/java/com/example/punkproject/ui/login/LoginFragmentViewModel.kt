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
            kotlin.runCatching {
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    _loginState.value = LoginState.Loading
                    _loginState.value = loginRepository.login(email, password)

                } else {
                    //alanları boş bırakmayın
                }
            }.onFailure {
                _loginState.value = LoginState.Error(it)
            }

        }
    }
}