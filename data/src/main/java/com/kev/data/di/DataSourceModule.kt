package com.kev.data.di

import com.kev.data.api.ContactApi
import com.kev.data.datasource.ContactDataSource
import com.kev.data.datasource.impl.ContactDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    @Singleton
    fun provideContactDataSource(contactApi: ContactApi): ContactDataSource {
        return ContactDataSourceImpl(contactApi)
    }
}