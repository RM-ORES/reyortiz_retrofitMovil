package com.example.retrofitMovil.ui.pantallaDetalle

import com.example.retrofitMovil.domain.modelo.Pedido
import com.example.retrofitMovil.ui.pantallaMaster.MasterEvent

sealed class DetalleEvent {
    class GetMesa(val id: Int) : DetalleEvent()
    class GetPedidosPorMesa(val id : Int) : DetalleEvent()
    class DeleteMesa(val id: Int) : DetalleEvent()

    class AddPedido(val pedido: Pedido) : DetalleEvent()
    object ErrorVisto : DetalleEvent()

}