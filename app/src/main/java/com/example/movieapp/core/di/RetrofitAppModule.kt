package com.example.movieapp.core.di

import com.example.movieapp.BuildConfig
import com.example.movieapp.core.service.RetrofitContentAPI
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitAppModule {
    private const val BASE_URL = "https://itunes.apple.com/"

    private fun createMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .client(provideOKHTTP())
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(createMoshi()))
            .build()
    }

    @Provides
    fun provideOKHTTP(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE))
            .build()
    }

    @Provides
    fun provideContentApi(retrofit: Retrofit): RetrofitContentAPI =
        retrofit.create(RetrofitContentAPI::class.java)
}