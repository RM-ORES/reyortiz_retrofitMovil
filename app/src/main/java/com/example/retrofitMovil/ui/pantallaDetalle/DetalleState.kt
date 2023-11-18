package com.example.retrofitMovil.ui.pantallaDetalle

import com.example.retrofitMovil.domain.modelo.Mesa
import com.example.retrofitMovil.domain.modelo.Pedido

data class DetalleState (
    val mesa: Mesa? = null,
    val pedidos: List<Pedido>,
    val error: String? = null,
    val selectedPedidos : List<Pedido> = emptyList(),
    val selectMode : Boolean = false
)