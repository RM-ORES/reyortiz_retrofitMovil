package com.example.retrofitMovil.data.repositories

import com.example.retrofitMovil.data.sources.PedidoRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PedidosRepository @Inject constructor(private val pedidoRemoteDataSource: PedidoRemoteDataSource) {
    suspend fun getPedidos(id: Int) = withContext(Dispatchers.IO){
        pedidoRemoteDataSource.getPedido(id)
    }
}