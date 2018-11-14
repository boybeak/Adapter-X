package com.github.boybeak.autobind

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

import com.github.boybeak.crashviewer.api.Return

interface ApiService {

    @GET("list")
    fun list(@Query("admin_id") admin_id: Int,
             @Query("role") role: String,
             @Query("studio_name") studio_name: String,
             @Query("page") page: Int,
             @Query("limit") limit: Int): Call<Return<List<Crash>>>

    @GET("get-info")
    fun getInfo(@Query("id") id: Int): Call<Return<Crash>>

}