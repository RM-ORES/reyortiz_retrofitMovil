package com.example.retrofitMovil.ui.pantallaMaster

import com.example.retrofitMovil.domain.modelo.Mesa

data class MasterState (
    val mesas: List<Mesa>,
    val error: String? = null,
    val selectedMesas: List<Mesa> = emptyList(),
    val selectMode: Boolean = false
    )