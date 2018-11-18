package com.lezhin.test.api

import com.lezhin.test.api.data.ImageApiData
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

private const val API_KEY = "86839ad628484dde158eced601366e06"
interface ImageService {
    @Headers("Authorization: KakaoAK $API_KEY")
    @GET("/v2/search/image")
    fun searchImages(
        @Query("query") query: String,
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 10
    ): Deferred<ImageApiData>
}
