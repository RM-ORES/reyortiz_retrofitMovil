package com.example.retrofitMovil.ui.pantallaActualizar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofitMovil.data.repositories.MesaRepository
import com.example.retrofitMovil.data.repositories.PedidosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ActualizarViewModel @Inject constructor(private val mesaRepository: MesaRepository,
    private val pedidosRepository: PedidosRepository) : ViewModel() {
    private val _uiState = MutableLiveData<ActualizarState>()
    val uiState: LiveData<ActualizarState> get() = _uiState
    init {
        _uiState.value = ActualizarState(mesa = null, pedidos = emptyList(), error = null)
    }
    fun handleEvent(event: ActualizarEvent){
        when(event){
            is ActualizarEvent.Actualizar -> TODO()
            is ActualizarEvent.AñadirPedido -> TODO()
            ActualizarEvent.GetMesa -> TODO()
        }
    }
    private fun getMesa(){}
    private fun getPedidos(){}

}