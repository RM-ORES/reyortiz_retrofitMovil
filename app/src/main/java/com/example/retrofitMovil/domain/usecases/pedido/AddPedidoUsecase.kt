package com.example.retrofitMovil.domain.usecases.pedido

import com.example.retrofitMovil.data.repositories.PedidosRepository
import com.example.retrofitMovil.domain.modelo.Pedido
import com.example.retrofitMovil.utilities.NetworkResult
import javax.inject.Inject

class AddPedidoUsecase @Inject constructor(private val pedidosRepository: PedidosRepository) {
    suspend operator fun invoke(pedido: Pedido): NetworkResult<Pedido> {
        return pedidosRepository.addPedido(pedido)
    }
}