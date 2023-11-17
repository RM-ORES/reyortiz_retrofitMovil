package com.example.retrofitMovil.data.model

import com.example.retrofitMovil.domain.modelo.Mesa
import com.google.gson.annotations.SerializedName

data class MesaResponse (
    @SerializedName("tableNumber")
    val tableNumber: Int,
    @SerializedName("seats")
    val seats: Int
)
fun MesaResponse.toMesa() : Mesa = Mesa(tableNumber,seats)