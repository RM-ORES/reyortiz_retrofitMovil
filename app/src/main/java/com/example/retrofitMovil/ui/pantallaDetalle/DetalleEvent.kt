package com.example.retrofitMovil.ui.pantallaDetalle

import com.example.retrofitMovil.domain.modelo.Pedido

sealed class DetalleEvent {
    class SetMesa(val id: Int) : DetalleEvent()
    class DeletePedido(val id: Int) : DetalleEvent()
    object DeleteMesa : DetalleEvent()

    class AddPedido(val pedido: Pedido) : DetalleEvent()
    object ErrorVisto : DetalleEvent()

}