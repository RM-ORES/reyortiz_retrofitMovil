package com.example.retrofitMovil.data.sources


import com.example.retrofitMovil.data.model.toPedido
import com.example.retrofitMovil.data.sources.services.PedidoService
import com.example.retrofitMovil.domain.modelo.Pedido
import com.example.retrofitMovil.utilities.Constantes
import com.example.retrofitMovil.utilities.NetworkResult
import javax.inject.Inject

class PedidoRemoteDataSource @Inject constructor(private val pedidoService: PedidoService) {
    suspend fun getPedidos(id :Int): NetworkResult<List<Pedido>> {
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
                return NetworkResult.Error(Constantes.NO_DATA)
            } else {
                return NetworkResult.Error("${response.code()} ${response.message()}")
            }
        } catch (e: Exception) {
            return NetworkResult.Error(e.message ?: e.toString())
        }
    }
    suspend fun addPedido(pedido: Pedido): NetworkResult<Pedido>{
        try{
            val response = pedidoService.addPedido(pedido)
            if (response.isSuccessful){
                val body = response.body()
                body?.let{
                    return NetworkResult.Success(it.toPedido())
                }
                return NetworkResult.Error(Constantes.NO_DATA)
            } else {
                return NetworkResult.Error("${response.code()} ${response.message()}")
            }
        } catch (e: Exception){
            return NetworkResult.Error(e.message ?: e.toString())
        }
    }
    suspend fun deletePedido(id:Int):NetworkResult<Unit>{
        try{
            val response = pedidoService.deletePedido(id)
            if (response.isSuccessful) {
                return NetworkResult.Success(Unit)
            } else {
                return NetworkResult.Error("${response.code()} ${response.message()}")
            }
        } catch (e: Exception){
            return NetworkResult.Error(e.message ?: e.toString())
        }
    }

}