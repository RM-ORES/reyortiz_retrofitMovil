package com.example.retrofitMovil.ui.pantallaMaster

import com.example.retrofitMovil.domain.modelo.Mesa
import com.example.retrofitMovil.ui.pantallaDetalle.DetalleEvent

sealed class MasterEvent {
    object GetMesas : MasterEvent()
    object ErrorVisto : MasterEvent()
    class DeleteMesa(val id: Int) : MasterEvent()
    class DeleteSeleccionadas() : MasterEvent()
    class SeleccionarMesa(val mesa: Mesa) : MasterEvent()
    class VerDetalle(val id : Int) : MasterEvent()
}