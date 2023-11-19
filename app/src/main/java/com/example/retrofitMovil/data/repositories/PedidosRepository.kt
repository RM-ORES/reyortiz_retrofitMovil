package com.example.retrofitMovil.data.repositories

import com.example.retrofitMovil.data.sources.PedidoRemoteDataSource
import com.example.retrofitMovil.domain.modelo.Pedido
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
@ActivityRetainedScoped
class PedidosRepository @Inject constructor(private val pedidoRemoteDataSource: PedidoRemoteDataSource) {
    suspend fun getPedidos(id: Int) = withContext(Dispatchers.IO){
        pedidoRemoteDataSource.getPedidos(id)
    }
    suspend fun addPedido(pedido: Pedido) = withContext(Dispatchers.IO){pedidoRemoteDataSource.addPedido(pedido)}
    suspend fun deletePedido(id: Int) = withContext(Dispatchers.IO){pedidoRemoteDataSource.deletePedido(id)}
}