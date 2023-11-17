package com.example.retrofitMovil.domain.modelo

import java.time.LocalDateTime

data class Pedido (
    val id: Int,
    val tableNumber: Int,
    val idCustomer: Int,
    val date: LocalDateTime
)

