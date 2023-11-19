package com.example.retrofitMovil.data.sources.di

import com.example.retrofitMovil.utilities.Constantes.BASE_URL
import com.example.retrofitMovil.data.sources.services.MesaService
import com.example.retrofitMovil.data.sources.services.PedidoService
import com.example.retrofitMovil.utilities.Constantes
import com.google.gson.GsonBuilder
import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {



    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create(GsonBuilder().setDateFormat(Constantes.DATE_FORMAT).setLenient().create())

    @Singleton
    @Provides
    fun provideConverterMoshiFactory(): MoshiConverterFactory =
        MoshiConverterFactory.create(Moshi.Builder().add(LocalDateTimeAdapter()).build())

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(moshiConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideCurrencyTableService(retrofit: Retrofit): MesaService =
        retrofit.create(MesaService::class.java)
    @Singleton
    @Provides
    fun provideCurrencyOrderService(retrofit: Retrofit): PedidoService =
        retrofit.create(PedidoService::class.java)

    class LocalDateTimeAdapter {
        @ToJson
        fun toJson(value: LocalDateTime): String {
            return FORMATTER.format(value)
        }

        @FromJson
        fun fromJson(value: String): LocalDateTime {
            return LocalDateTime.parse(value,FORMATTER)
        }

        companion object {
            private val FORMATTER = DateTimeFormatter.ofPattern(Constantes.DATE_FORMAT)
        }
    }
}