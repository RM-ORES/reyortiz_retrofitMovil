package com.example.retrofitMovil.data.sources.services

import com.example.retrofitMovil.utilities.Constantes
import com.example.retrofitMovil.data.model.MesaResponse
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface MesaService {
    @GET(Constantes.ALLMESAS)
    suspend fun getAllMesas():Response<List<MesaResponse>>

    @GET(Constantes.MESA_ID)
    suspend fun getMesa(@Path(Constantes.ID) id :Int):Response<MesaResponse>

    @DELETE(Constantes.MESA_ID)
    suspend fun deleteMesa(@Path(Constantes.ID) id: Int): Response<Unit>
}