package com.codebros.eripple.data.url

import com.codebros.eripple.data.network.ApiService
import com.codebros.eripple.data.network.NullOnEmptyConverterFactory
import com.codebros.eripple.data.url.DefaultUrl.SERVER_URL
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitGenerator {

    private fun getClient(): Retrofit =
        Retrofit.Builder()
            .baseUrl(SERVER_URL)
            //.baseUrl(LOCAL_URL)
            .client(buildOkHttpClient())
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(provideGsonConvertFactory())
            .build()

    private fun provideGsonConvertFactory(): GsonConverterFactory {
        return GsonConverterFactory.create(
            GsonBuilder()
                .setLenient()
                .create()
        )
    }

    private fun buildOkHttpClient(): OkHttpClient {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES)
            .writeTimeout(5, TimeUnit.MINUTES)
            .addInterceptor(interceptor)
            .build()
    }

    fun getApiService(): ApiService = getClient().create(ApiService::class.java)


}