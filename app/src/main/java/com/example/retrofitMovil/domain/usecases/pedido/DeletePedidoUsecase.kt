package com.example.retrofitMovil.domain.usecases.pedido

import com.example.retrofitMovil.data.repositories.PedidosRepository
import com.example.retrofitMovil.utilities.NetworkResult
import javax.inject.Inject

class DeletePedidoUsecase @Inject constructor(private val pedidosRepository: PedidosRepository) {
    suspend operator fun invoke(id: Int): NetworkResult<Unit> {
        return pedidosRepository.deletePedido(id)
    }
}