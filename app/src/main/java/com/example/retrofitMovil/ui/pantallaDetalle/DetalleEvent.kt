package com.example.retrofitMovil.ui.pantallaDetalle

import com.example.retrofitMovil.ui.pantallaMaster.MasterEvent

sealed class DetalleEvent {
    class GetMesa(val id: Int) : DetalleEvent()
    class GetPedidosPorMesa(val id : Int) : DetalleEvent()
    class DeleteMesa(val id: Int) : DetalleEvent()

    class Actualizar(val id: Int) : DetalleEvent()
    object ErrorVisto : DetalleEvent()

}