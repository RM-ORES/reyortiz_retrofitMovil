package com.example.retrofitMovil.data.repositories

import com.example.retrofitMovil.data.sources.MesaRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MesaRepository @Inject constructor(private val mesaRemoteDataSource: MesaRemoteDataSource) {
    suspend fun getAllMesas() = withContext(Dispatchers.IO){mesaRemoteDataSource.getAllMesas()}
}