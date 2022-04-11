package com.app.picpayapp.services

import com.app.picpayapp.data.LoggedUser
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

// Static constant
private const val URL = "http://10.0.0.112:8080/"

object RetrofitService {

    private fun newHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addNetworkInterceptor(httpLoggingInterceptor)
            .addInterceptor { chain ->
                val request = applyToken(chain)
                chain.proceed(request)
            }
            .connectTimeout(1, TimeUnit.SECONDS)
            .readTimeout(40, TimeUnit.SECONDS)
            .writeTimeout(40, TimeUnit.SECONDS)
            .build()
    }

    private fun applyToken(chain: Interceptor.Chain): Request {
        return if (LoggedUser.isLogged()) {
            val token = LoggedUser.token
            chain.request()
                .newBuilder()
                .addHeader("Authorization", "${token.type} ${token.token}")
                .build()
        } else {
            chain.request()
        }
    }

    val service: Retrofit = Retrofit.Builder()
        .client(newHttpClient())
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // The generic variable T will be passed at reified time,
    // not in compilation time as expected by JVM
    inline fun <reified T> create() = service.create(T::class.java)
}