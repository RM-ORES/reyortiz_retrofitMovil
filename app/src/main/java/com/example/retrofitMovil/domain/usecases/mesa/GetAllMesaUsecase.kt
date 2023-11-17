package com.example.retrofitMovil.domain.usecases.mesa

import com.example.retrofitMovil.data.repositories.MesaRepository
import com.example.retrofitMovil.domain.modelo.Mesa
import javax.inject.Inject

class GetAllMesaUsecase @Inject constructor(private val mesaRepository: MesaRepository){
    suspend operator fun invoke(): () -> Unit {
        return mesaRepository.getAllMesas()
    }
}