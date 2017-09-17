package com.gotasoft.movies.data.source.remote.api

import android.content.Context
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.gotasoft.movies.R
import com.gotasoft.movies.data.Movie
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface ApiRemoteDataSource {


    @GET("user")
    fun getMovie(@Query("id") id: String,
                 @Query("version") version: String,
                 @Query("lang") lang: String): Observable<List<Movie>>

    object Factory {

        private var apiRemoteDataSource: ApiRemoteDataSource? = null

        fun create(context: Context): ApiRemoteDataSource {
            if (apiRemoteDataSource == null) {
                val gson = GsonBuilder()
                        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                        .create()

                val retrofit = Retrofit.Builder()
                        .baseUrl(context.getString(R.string.domain))
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build()

                apiRemoteDataSource = retrofit.create(ApiRemoteDataSource::class.java)
            }
            return apiRemoteDataSource as ApiRemoteDataSource
        }
    }

}
