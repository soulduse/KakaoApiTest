package com.lezhin.test.api.data

import com.google.gson.annotations.SerializedName

data class ImageApiData(
    val documents: ArrayList<Document>,
    val meta: Meta
)

data class Document(
    val collection: String,
    val datetime: String,
    @SerializedName("display_sitename")
    val displaySitename: String,
    @SerializedName("doc_url")
    val docUrl: String,
    val height: Int,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("thumbnail_url")
    val thumbnailUrl: String,
    val width: Int
)

data class Meta (
    @SerializedName("is_end")
    val isEnd: Boolean,
    @SerializedName("pageable_count")
    val pageableCount: Int,
    @SerializedName("total_count")
    val totalCount: Int
)
