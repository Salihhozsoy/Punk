package com.example.punkproject.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.punkproject.data.locale.UserEntity
import com.example.punkproject.data.repository.UserRepository
import com.example.punkproject.data.state.RegisterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterFragmentViewModel @Inject constructor(private val userRepository: UserRepository):ViewModel() {

    private val _registerState:MutableStateFlow<RegisterState> = MutableStateFlow(RegisterState.Idle)
    val registerState:StateFlow<RegisterState> = _registerState

    fun register(email:String,password:String){
        viewModelScope.launch {
            kotlin.runCatching {
                _registerState.value =RegisterState.Loading
                if(email.isNotEmpty() && password.isNotEmpty()){
                    if(userRepository.getUserByEmail(email)==null){
                        var userEntity =UserEntity(email=email, password = password)
                        userRepository.insert(userEntity)
                        _registerState.value = RegisterState.Result(userEntity)
                    }else{
                        // kullanıcı zaten var
                    }
                }else{
                    // boş alan bırakmayınız
                }
            }.onFailure {
                _registerState.value = RegisterState.Error(it)
            }
        }
    }
}