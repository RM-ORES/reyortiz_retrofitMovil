package com.example.retrofitMovil.data.sources

import com.example.retrofitMovil.data.model.toPedido
import com.example.retrofitMovil.data.sources.services.PedidoService
import com.example.retrofitMovil.domain.modelo.Pedido
import com.example.retrofitMovil.utilities.NetworkResult
import javax.inject.Inject

class PedidoRemoteDataSource @Inject constructor(private val pedidoService: PedidoService) {
    suspend fun getPedido(id :Int): NetworkResult<List<Pedido>> {
        try {
            val response = pedidoService.getPedidos(id)
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    val pedidos = it.map { pedidoResponse ->
                        pedidoResponse.toPedido()
                    }
                    return NetworkResult.Success(pedidos)
                }
                return error("No data")
            }
            return error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }
}