package edu.ucne.checkworcks.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.ucne.checkworcks.Repository.TareaRepositoryImp
import edu.ucne.checkworcks.data.remote.TareaApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun providesMoshi(): Moshi {
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    @Singleton
    @Provides
    fun providesTareasApi(moshi: Moshi): TareaApi {
        return Retrofit.Builder()
            .baseUrl("https://www.wapicw.somee.com/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(TareaApi::class.java)
    }
    @Provides
    @Singleton
    fun provideParqueosRepository(api: TareaApi) : TareaRepositoryImp{
        return TareaRepositoryImp(api)
    }

}