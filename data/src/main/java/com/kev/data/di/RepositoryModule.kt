package com.kev.data.di

import com.kev.data.datasource.remote.ContactDataSource
import com.kev.data.repository.ContactRepositoryImpl
import com.kev.domain.repository.ContactRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideContactRepository(contactDataSource: ContactDataSource): ContactRepository {
        return ContactRepositoryImpl(contactDataSource)
    }
}