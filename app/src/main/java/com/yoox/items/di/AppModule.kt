package com.yoox.items.di

import android.app.Application
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.yoox.items.App
import com.yoox.items.R
import com.yoox.items.app.api.Api
import com.yoox.items.app.api.ApiResponseHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CookieManager
import java.net.CookiePolicy
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideApi(
        app: App,
    ): Api {
        val baseUrl = app.getString(R.string.base_api_url)

        val client = getOkHttpClient()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(Api::class.java)
    }

    private fun addLoggingInterceptor(httpClientBuilder: OkHttpClient.Builder) {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        httpClientBuilder.addInterceptor(httpLoggingInterceptor)
    }

    private fun getOkHttpClient(
    ): OkHttpClient {
        val cookieManager = CookieManager()
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL)

        val builder = OkHttpClient.Builder()
        builder.connectTimeout(10, TimeUnit.SECONDS)
        builder.readTimeout(1, TimeUnit.MINUTES)
        builder.writeTimeout(30, TimeUnit.SECONDS)

        addLoggingInterceptor(builder)
        builder.connectionSpecs(
            listOf(
                ConnectionSpec.MODERN_TLS,
                ConnectionSpec.COMPATIBLE_TLS,
                ConnectionSpec.CLEARTEXT
            )
        )

        return builder.build()
    }

    @Singleton
    @Provides
    fun provideApp(app: Application): App {
        return app as App
    }

    @Singleton
    @Provides
    fun provideSharedPreferences(app: Application): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(app)
    }

//    @Singleton
//    @Provides
//    fun provideSharedPreferenceProvider(sharedPreferences: SharedPreferences): SharedPreferenceProvider {
//        return UtilitaSharedPreferences(sharedPreferences)
//    }

    //region handlers
    @Singleton
    @Provides
    fun provideApiResponseHandler(): ApiResponseHandler<*> {
        return ApiResponseHandler<Any>()
    }
    //endregion handlers
}
