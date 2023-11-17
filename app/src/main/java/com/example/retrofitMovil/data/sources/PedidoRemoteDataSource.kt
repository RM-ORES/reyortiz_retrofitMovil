package com.example.retrofitMovil.data.sources

import com.example.retrofitMovil.data.sources.services.PedidoService
import javax.inject.Inject

class PedidoRemoteDataSource @Inject constructor(private val pedidoService: PedidoService) {
}