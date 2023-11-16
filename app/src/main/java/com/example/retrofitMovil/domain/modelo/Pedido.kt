package com.example.retrofitMovil.domain.modelo

import java.time.LocalDate

data class Pedido (
    val id: Int,
    val tableNumber: Int,
    val idCustomer: Int,
    val date: LocalDate
)

