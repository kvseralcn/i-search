package com.kevser.isearch.core.di

import com.kevser.isearch.core.service.ContentApi
import com.kevser.isearch.core.service.ContentApiImpl
import com.kevser.isearch.core.service.ContentRepository
import com.kevser.isearch.core.service.ContentRepositoryImpl
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