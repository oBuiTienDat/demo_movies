package com.gotasoft.movies.data.source.remote.api

import android.content.Context

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.gotasoft.movies.R
import com.gotasoft.movies.data.Category
import com.gotasoft.movies.data.Product

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 * Created by FRAMGIA\bui.tien.dat on 18/09/2017.
 */

interface ApiRemoteDataSource {

    @GET("/atests/getProducts")
    fun getProduct(@Query("id") id: String,
                   @Query("category") category: String,
                   @Query("version") version: String,
                   @Query("lang") lang: String): Observable<List<Product>>

    @GET("/atests/getCategories")
    fun getCategories(@Query("id") id: String,
                      @Query("version") version: String,
                      @Query("lang") lang: String): Observable<List<Category>>

    object Factory {

        private var apiRemoteDataSource: ApiRemoteDataSource? = null

        fun create(context: Context): ApiRemoteDataSource {
            if (apiRemoteDataSource == null) {
                val gson = GsonBuilder()
                        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                        .create()

                val retrofit = Retrofit.Builder()
                        .baseUrl(context.getString(R.string.domain))
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                        .build()

                apiRemoteDataSource = retrofit.create(ApiRemoteDataSource::class.java)
            }
            return apiRemoteDataSource as ApiRemoteDataSource
        }
    }
}
