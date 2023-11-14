package com.example.retrofitMovil.ui.pantallaActualizar

import com.example.retrofitMovil.domain.modelo.Mesa
import com.example.retrofitMovil.domain.modelo.Pedido

sealed class ActualizarEvent {
    object GetMesa : ActualizarEvent()
    class AñadirPedido(val pedido:Pedido) : ActualizarEvent()
    class Actualizar(val mesa: Mesa) : ActualizarEvent()
}