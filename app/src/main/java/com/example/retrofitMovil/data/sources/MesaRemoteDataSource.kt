package com.example.retrofitMovil.data.sources

import com.example.retrofitMovil.data.model.toMesa
import com.example.retrofitMovil.data.sources.services.MesaService
import com.example.retrofitMovil.domain.modelo.Mesa
import com.example.retrofitMovil.utilities.Constantes
import com.example.retrofitMovil.utilities.NetworkResult
import javax.inject.Inject


class MesaRemoteDataSource @Inject constructor(private val mesaService: MesaService) {
    suspend fun getAllMesas(): NetworkResult<List<Mesa>> {
        try {
            val response = mesaService.getAllMesas()
            if (response.isSuccessful) {
                response.body()?.let {
                    val mesas = it.map { mesaResponse ->
                        mesaResponse.toMesa()
                    }
                    return NetworkResult.Success(mesas)
                }
                return NetworkResult.Error(Constantes.NO_DATA)
            } else {
                return NetworkResult.Error("${response.code()} ${response.message()}")
            }
        } catch (e: Exception) {
            return NetworkResult.Error(e.message ?: e.toString())
        }
    }

    suspend fun getMesa(id: Int): NetworkResult<Mesa> {
        try {
            val response = mesaService.getMesa(id)
            if (response.isSuccessful) {
                response.body()?.let {
                    val mesa = it.toMesa()
                    return NetworkResult.Success(mesa)
                }
                return NetworkResult.Error(Constantes.NO_DATA)
            } else {
                return NetworkResult.Error("${response.code()} ${response.message()}")
            }
        } catch (e: Exception) {
            return NetworkResult.Error(e.message ?: e.toString())
        }
    }

    suspend fun deleteMesa(id: Int): NetworkResult<Unit> {
        try {
            val response = mesaService.deleteMesa(id)
            if (response.isSuccessful) {
                return NetworkResult.Success(Unit)
            } else {
                return NetworkResult.Error("${response.code()} ${response.message()}")
            }
        } catch (e: Exception){
            return NetworkResult.Error(e.message ?: e.toString())
        }
    }
}