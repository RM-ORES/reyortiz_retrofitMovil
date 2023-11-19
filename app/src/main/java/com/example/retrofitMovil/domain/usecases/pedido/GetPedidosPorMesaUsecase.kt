package com.example.retrofitMovil.domain.usecases.pedido

import com.example.retrofitMovil.data.repositories.PedidosRepository
import com.example.retrofitMovil.domain.modelo.Pedido
import com.example.retrofitMovil.utilities.NetworkResult
import javax.inject.Inject

class GetPedidosPorMesaUsecase @Inject constructor(private val pedidosRepository: PedidosRepository){
    suspend operator fun invoke(id : Int): NetworkResult<List<Pedido>> {
        return pedidosRepository.getPedidos(id)
    }
}