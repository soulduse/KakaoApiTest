package com.lezhin.test.api

import com.dave.network.ApiProvider

object ApiProvider {
    private const val HOST = "https://dapi.kakao.com"

    fun apiProvider(): ImageService = ApiProvider.provideApi(
                service = ImageService::class.java,
                baseUrl = HOST,
                isDebug = true)
}
