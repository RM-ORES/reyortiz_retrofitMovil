package com.example.retrofitMovil.data.sources

import com.example.retrofitMovil.data.model.toMesa
import com.example.retrofitMovil.data.sources.services.MesaService
import com.example.retrofitMovil.domain.modelo.Mesa
import com.example.retrofitMovil.utilities.NetworkResult
import javax.inject.Inject

class MesaRemoteDataSource @Inject constructor(private val mesaService: MesaService) {
    suspend fun getAllMesas(): NetworkResult<List<Mesa>>{
        try {
            val response = mesaService.getAllMesas()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    val mesas = it.map { mesaResponse ->
                        mesaResponse.toMesa()
                    }
                    return NetworkResult.Success(mesas)
                }
                return error()
            }
            return error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }
}