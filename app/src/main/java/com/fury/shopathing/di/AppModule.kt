package com.fury.shopathing.di

import com.fury.shopathing.data.remote.ShopApi
import com.fury.shopathing.data.repository.ProductRepositoryImpl
import com.fury.shopathing.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideShopApi(): ShopApi {
        return Retrofit.Builder()
            .baseUrl("https://api.escuelajs.co/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ShopApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProductRepository(api: ShopApi): ProductRepository {
        return ProductRepositoryImpl(api)
    }
}