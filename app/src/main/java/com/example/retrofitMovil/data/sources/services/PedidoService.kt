package com.example.retrofitMovil.data.sources.services

import com.example.retrofitMovil.utilities.Constantes
import com.example.retrofitMovil.data.model.PedidoResponse
import com.example.retrofitMovil.domain.modelo.Pedido
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PedidoService {
    @GET(Constantes.PEDIDOS_POR_MESA)
    suspend fun getPedidos(id: Int): Response<List<PedidoResponse>>

    @POST(Constantes.ALLPEDIDOS)
    suspend fun addPedido(pedido: Pedido): Response<>

    @DELETE(Constantes.PEDIDO_ID)
    suspend fun deletePedido(@Path(Constantes.ID) id: Int): Response<>
}
}