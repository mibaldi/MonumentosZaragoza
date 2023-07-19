package com.mibaldi.monumentoszaragoza.data.server

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RemoteService {

    @GET("monumento.json?fl=id,title,geometry&srsname=wgs84")
    suspend fun getMonumentos(@Query("point")point: String,@Query("distance")distance: String = "250"): RemoteResult
    @GET("monumento/{id}")
    suspend fun getMonumento(@Path("id") id: Int): RemoteMonumento
}
