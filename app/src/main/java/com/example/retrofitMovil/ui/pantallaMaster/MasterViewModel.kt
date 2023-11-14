package com.example.retrofitMovil.ui.pantallaMaster

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofitMovil.data.repositories.MesaRepository
import com.example.retrofitMovil.data.repositories.PedidosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MasterViewModel @Inject constructor(private val mesaRepository: MesaRepository) : ViewModel(){
    private val _uiState = MutableLiveData<MasterState>()
    val uiState: LiveData<MasterState> get() = _uiState
    init{
        _uiState.value = MasterState(mesas = emptyList(), error = null)
    }
    fun handleEvent(event: MasterEvent){
        when(event){
            MasterEvent.ErrorVisto -> _uiState.value = _uiState.value?.copy(error = null)
            MasterEvent.GetMesas-> {getMesas()}
            is MasterEvent.DeleteMesa -> TODO()
            is MasterEvent.DeleteSeleccionadas -> TODO()
            is MasterEvent.SeleccionarMesa -> TODO()
            is MasterEvent.VerDetalle -> TODO()
        }
    }
    private fun getMesas(){}
}