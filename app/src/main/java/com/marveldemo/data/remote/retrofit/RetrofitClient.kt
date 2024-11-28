package com.marveldemo.data.remote.retrofit

import com.marveldemo.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.concurrent.TimeUnit

private const val API_KEY = "apiKey"
private const val CONNECT_TIMEOUT = 30L
private const val MD5 = "MD5"
private const val HASH = "hash"
private const val READ_TIMEOUT = 30L
private const val TIMESTAMP = "ts"
private const val TIMESTAMP_DEFAULT = "1"
private const val WRITE_TIMEOUT = 10L
private const val BASE_URL = "https://gateway.marvel.com/"

class RetrofitClient {
    private val requestIntercept = { chain: Interceptor.Chain ->
        val original = chain.request()
        val originalHttpUrl = original.url
        val url = originalHttpUrl.newBuilder()
            .addQueryParameter(TIMESTAMP, TIMESTAMP_DEFAULT)
            .addQueryParameter(HASH, getHash()) //md5(ts+privateKey+publicKey)
            .addQueryParameter(API_KEY, BuildConfig.API_KEY)
            .build()

        val requestBuilder = original.newBuilder().url(url)
        val request = requestBuilder.build()

        chain.proceed(request)
    }

    private val client: OkHttpClient
        get() {
            val okHttp = OkHttpClient().newBuilder()
                .addInterceptor(requestIntercept)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            okHttp.addInterceptor(loggingCapableHttpClient)

            return okHttp.build()
        }

    private val loggingCapableHttpClient: HttpLoggingInterceptor
        get() {
            val mLogging = HttpLoggingInterceptor()
            mLogging.level = HttpLoggingInterceptor.Level.BODY

            return mLogging
        }

    fun create(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getHash(): String {
        val s = BuildConfig.HASH
        if (s.isNotEmpty()) {
            val md5 = "MD5"
            try { // Create MD5 Hash
                val digest = MessageDigest.getInstance(MD5)
                digest.update(s.toByteArray())
                val messageDigest = digest.digest()
                // Create Hex String
                val hexString = StringBuilder()
                for (aMessageDigest in messageDigest) {
                    var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
                    while (h.length < 2) h = "0$h"
                    hexString.append(h)
                }
                return hexString.toString()
            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()
            }
        }
        return ""
    }
}