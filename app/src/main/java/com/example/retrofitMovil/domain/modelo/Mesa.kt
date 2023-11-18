package com.example.retrofitMovil.domain.modelo

import com.example.retrofitMovil.utilities.Constantes

data class Mesa (
    val tableNumber: Int,
    val seats: Int,
){
    override fun toString(): String {
        return Constantes.MESA + tableNumber
    }
}