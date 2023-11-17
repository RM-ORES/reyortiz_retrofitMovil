package com.example.retrofitMovil.data.repositories

import com.example.retrofitMovil.data.sources.PedidoRemoteDataSource
import javax.inject.Inject

class PedidosRepository @Inject constructor(private val pedidoRemoteDataSource: PedidoRemoteDataSource) {
    suspend fun getPedidos()
}