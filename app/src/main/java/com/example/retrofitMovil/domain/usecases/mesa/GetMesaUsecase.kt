package com.example.retrofitMovil.domain.usecases.mesa

import com.example.retrofitMovil.data.repositories.MesaRepository
import com.example.retrofitMovil.domain.modelo.Mesa
import com.example.retrofitMovil.utilities.NetworkResult
import javax.inject.Inject

class GetMesaUsecase @Inject constructor(private val mesaRepository: MesaRepository){
    suspend operator fun invoke(id : Int): NetworkResult<Mesa> {
        return mesaRepository.getMesa(id)
    }
}