package com.example.retrofitMovil.domain.modelo

import com.example.retrofitMovil.utilities.Constantes
import java.time.LocalDateTime

data class Pedido (
    val id: Int,
    val tableNumber: Int,
    val idCustomer: Int,
    val date: LocalDateTime,
){
    override fun toString(): String {
        return Constantes.ID_UI + id + Constantes.COMA + date + Constantes.COMA + Constantes.CLIENTE
    }
}

