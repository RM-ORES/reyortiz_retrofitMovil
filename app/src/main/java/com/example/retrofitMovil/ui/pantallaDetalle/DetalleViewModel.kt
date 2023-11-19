package com.example.retrofitMovil.ui.pantallaDetalle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitMovil.domain.modelo.Mesa
import com.example.retrofitMovil.domain.modelo.Pedido
import com.example.retrofitMovil.domain.usecases.mesa.DeleteMesaUsecase
import com.example.retrofitMovil.domain.usecases.mesa.GetMesaUsecase
import com.example.retrofitMovil.domain.usecases.pedido.AddPedidoUsecase
import com.example.retrofitMovil.domain.usecases.pedido.DeletePedidoUsecase
import com.example.retrofitMovil.domain.usecases.pedido.GetPedidosPorMesaUsecase
import com.example.retrofitMovil.utilities.Constantes
import com.example.retrofitMovil.utilities.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class DetalleViewModel @Inject constructor(
    private val getMesaUsecase: GetMesaUsecase,
    private val getPedidosPorMesaUsecase: GetPedidosPorMesaUsecase,
    private val addPedidoUsecase: AddPedidoUsecase,
    private val deletePedidoUsecase: DeletePedidoUsecase,
    private val deleteMesaUsecase: DeleteMesaUsecase
) : ViewModel() {
    private val _uiState = MutableLiveData<DetalleState>()
    val uiState: LiveData<DetalleState> get() = _uiState

    init {
        _uiState.value = DetalleState(mesa = null, pedidos = emptyList(), error = null)
    }

    fun handleEvent(event: DetalleEvent) {
        when (event) {
            DetalleEvent.ErrorVisto -> _uiState.value = _uiState.value?.copy(error = null)
            is DetalleEvent.SetMesa -> setMesa(event.id)
            is DetalleEvent.AddPedido -> addPedido(event.pedido)
            is DetalleEvent.DeletePedido -> deletePedido(event.id)
            DetalleEvent.DeleteMesa -> deleteMesa()
        }
    }

    private fun setMesa(id: Int) {
        viewModelScope.launch {
            val mesa = getMesaUsecase(id)
            val pedidos = getPedidosPorMesaUsecase(id)
            Timber.i(pedidos.message)
            when (mesa) {
                is NetworkResult.Success -> {
                    when (pedidos) {
                        is NetworkResult.Success -> {
                            _uiState.value = _uiState.value?.copy(
                                mesa = mesa.data as Mesa,
                                pedidos = pedidos.data as List<Pedido>
                            )
                        }

                        is NetworkResult.Error -> {
                            _uiState.value = _uiState.value?.copy(
                                mesa = mesa.data as Mesa,
                                error = Constantes.NO_PEDIDOS
                            )
                        }
                    }
                }

                is NetworkResult.Error -> {
                    _uiState.value = _uiState.value?.copy(error = pedidos.message.toString())
                }
            }
        }
    }

    private fun addPedido(pedido: Pedido) {
        viewModelScope.launch {
            when (addPedidoUsecase(pedido)) {
                is NetworkResult.Error -> _uiState.value =
                    _uiState.value?.copy(error = Constantes.ERROR)

                is NetworkResult.Success -> {
                    _uiState.value = _uiState.value?.copy(error = Constantes.ANADIDO)
                    _uiState.value?.mesa?.let { setMesa(it.tableNumber) }
                }

            }
        }
    }

    private fun deletePedido(id: Int) {
        viewModelScope.launch {
            when (deletePedidoUsecase(id)) {
                is NetworkResult.Error -> _uiState.value =
                    _uiState.value?.copy(error = Constantes.ERROR)

                is NetworkResult.Success -> {
                    _uiState.value = _uiState.value?.copy(error = Constantes.BORRADO_P)
                    _uiState.value?.mesa?.let { setMesa(it.tableNumber) }
                }
            }
        }
    }

    private fun deleteMesa() {
        viewModelScope.launch {
            _uiState.value?.mesa?.let {
                when (deleteMesaUsecase(it.tableNumber)) {
                    is NetworkResult.Error -> _uiState.value =
                        _uiState.value?.copy(error = Constantes.ERROR)

                    is NetworkResult.Success -> _uiState.value =
                        _uiState.value?.copy(error = Constantes.BORRADO_M, fin = true)
                }
            }

        }
    }

}