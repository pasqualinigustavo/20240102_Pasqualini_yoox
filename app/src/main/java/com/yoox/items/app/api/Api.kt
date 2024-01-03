package com.yoox.items.app.api

import com.yoox.items.app.api.response.GetItemsResponse
import com.yoox.items.app.api.response.RemoteItemDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * REST API access points
 * Using flow call arch
 */
interface Api {

    @GET("pdp/{cod}")
    suspend fun getItem(
        @Path("cod") cod: String
    ): Response<RemoteItemDetails>

    @GET("plp/page/{page}")
    suspend fun getItems(@Path("page") page: String): Response<GetItemsResponse>

}
