package com.example.retrofitMovil.ui.pantallaMaster

import com.example.retrofitMovil.domain.modelo.Mesa

sealed class MasterEvent {
    object GetMesas : MasterEvent()
    object ErrorVisto : MasterEvent()

    object StartSelectMode: MasterEvent()
    object ResetSelectMode: MasterEvent()


    class DeleteMesa(val id: Int) : MasterEvent()
    object DeleteSeleccionadas : MasterEvent()
    class SeleccionarMesa(val mesa: Mesa) : MasterEvent()
    class RemoveSeleccionada(val mesa: Mesa) : MasterEvent()
}