package com.example.retrofitMovil.data.repositories

import com.example.retrofitMovil.data.sources.MesaRemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ActivityRetainedScoped
class MesaRepository @Inject constructor(private val mesaRemoteDataSource: MesaRemoteDataSource) {
    suspend fun getAllMesas() = withContext(Dispatchers.IO){mesaRemoteDataSource.getAllMesas()}
    suspend fun getMesa(id :Int) = withContext(Dispatchers.IO){mesaRemoteDataSource.getMesa(id)}
    suspend fun deleteMesa(id:Int) = withContext(Dispatchers.IO){mesaRemoteDataSource.deleteMesa(id)}
}