package com.example.retrofitMovil.data.model

import com.example.retrofitMovil.domain.modelo.Pedido
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class PedidoResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("idCustomer")
    val idCustomer: Int,
    @SerializedName("tableNumber")
    val tableNumber: Int,
    @SerializedName("date")
    val date: LocalDateTime
)
fun PedidoResponse.toPedido() : Pedido = Pedido(id, tableNumber,idCustomer, date)
