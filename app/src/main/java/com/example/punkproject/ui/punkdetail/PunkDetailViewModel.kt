package com.example.punkproject.ui.punkdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.punkproject.data.model.ResponseItem
import com.example.punkproject.data.repository.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PunkDetailViewModel @Inject constructor(private val photoRepository: PhotoRepository):ViewModel() {

    private val _punkDetailState: MutableStateFlow<ResponseItem?> = MutableStateFlow(null)
    val punkDetailState: StateFlow<ResponseItem?> =_punkDetailState
    fun getPhotoById(id:Int){
        viewModelScope.launch {
            runCatching {
                _punkDetailState.value=(photoRepository.getPhotosById(id))
            }
        }
    }
}