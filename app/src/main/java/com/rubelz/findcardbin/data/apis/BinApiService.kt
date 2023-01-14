package com.rubelz.findcardbin.data.apis

import com.rubelz.findcardbin.data.models.Card
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BinApiService {

    @GET("/{id}")
    suspend fun getCard(@Path("id") bin: Int): Response<Card>

}