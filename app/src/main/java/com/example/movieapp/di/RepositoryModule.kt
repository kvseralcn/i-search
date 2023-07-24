package com.example.movieapp.di

import com.example.movieapp.service.ContentApi
import com.example.movieapp.service.ContentApiImpl
import com.example.movieapp.service.ContentRepository
import com.example.movieapp.service.ContentRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {


    @Binds
    abstract fun provideContentRepository(
        contentRepository: ContentRepositoryImpl
    ): ContentRepository

    @Binds
    abstract fun provideContentApi(
        contentApi: ContentApiImpl
    ): ContentApi
}