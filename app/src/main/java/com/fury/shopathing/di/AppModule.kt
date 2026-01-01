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
import com.google.firebase.auth.FirebaseAuth
import com.fury.shopathing.domain.repository.AuthRepository
import com.fury.shopathing.data.repository.AuthRepositoryImpl
import android.app.Application
import android.content.Context
import androidx.room.Room
import com.fury.shopathing.data.local.ShopDatabase
import com.fury.shopathing.data.local.CartDao
import com.fury.shopathing.data.repository.CartRepositoryImpl
import com.fury.shopathing.domain.repository.CartRepository
import com.fury.shopathing.data.local.UserPreferences
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideShopApi(): ShopApi {
        return Retrofit.Builder()
            .baseUrl("https://dummyjson.com/") // Switch to DummyJSON
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ShopApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProductRepository(api: ShopApi): ProductRepository {
        return ProductRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideAuthRepository(firebaseAuth: FirebaseAuth): AuthRepository {
        return AuthRepositoryImpl(firebaseAuth)
    }

    @Provides
    @Singleton
    fun provideShopDatabase(app: Application): ShopDatabase {
        return Room.databaseBuilder(
            app,
            ShopDatabase::class.java,
            "shop_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCartDao(db: ShopDatabase): CartDao {
        return db.cartDao()
    }

    @Provides
    @Singleton
    fun provideCartRepository(dao: CartDao): CartRepository {
        return CartRepositoryImpl(dao)
    }

    @Provides
    @Singleton
    fun provideUserPreferences(@ApplicationContext context: Context): UserPreferences {
        return UserPreferences(context)
    }
}