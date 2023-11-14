package com.example.retrofitMovil.ui.pantallaDetalle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofitMovil.data.repositories.MesaRepository
import com.example.retrofitMovil.data.repositories.PedidosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class DetalleViewModel @Inject constructor(private val mesaRepository: MesaRepository,
                                           private val pedidosRepository: PedidosRepository) : ViewModel(){
    private val _uiState = MutableLiveData<DetalleState>()
    val uiState: LiveData<DetalleState> get() = _uiState
    init{
        _uiState.value = DetalleState(mesa = null, pedidos = emptyList(), error = null)
    }
    fun handleEvent(event: DetalleEvent){
        when(event){
            is DetalleEvent.Actualizar -> TODO()
            is DetalleEvent.DeleteMesa -> TODO()
            DetalleEvent.ErrorVisto -> TODO()
            is DetalleEvent.GetMesa -> TODO()
            is DetalleEvent.GetPedidosPorMesa -> TODO()
        }
    }
    private fun getMesa(){}
    private fun getPedidos(){}
}