package com.example.retrofitMovil.ui.pantallaActualizar

import com.example.retrofitMovil.domain.modelo.Mesa
import com.example.retrofitMovil.domain.modelo.Pedido

data class ActualizarState (
    val mesa: Mesa? = null,
    val pedidos: List<Pedido>,
    val error: String? = null
)