package com.example.retrofitMovil.domain.usecases.mesa

import com.example.retrofitMovil.data.repositories.MesaRepository
import com.example.retrofitMovil.utilities.NetworkResult
import javax.inject.Inject

class DeleteMesaUsecase @Inject constructor(private val mesaRepository: MesaRepository) {
    suspend operator fun invoke(id: Int): NetworkResult<Unit>{
        return mesaRepository.deleteMesa(id)
    }
}