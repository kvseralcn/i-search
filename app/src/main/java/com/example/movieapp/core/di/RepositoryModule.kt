package com.example.movieapp.core.di

import com.example.movieapp.core.service.ContentApi
import com.example.movieapp.core.service.ContentApiImpl
import com.example.movieapp.core.service.ContentRepository
import com.example.movieapp.core.service.ContentRepositoryImpl
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