package com.example.retrofitMovil.domain.usecases.pedido

import com.example.retrofitMovil.data.repositories.MesaRepository
import com.example.retrofitMovil.data.repositories.PedidosRepository
import javax.inject.Inject

class GetPedidosPorMesaUsecase @Inject constructor(private val pedidosRepository: PedidosRepository){
}