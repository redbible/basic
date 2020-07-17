package mobile.nftf.network.model

import java.util.*

data class ImageKakao(
    val collection: String,
    val datetime: Date,
    val display_sitename: String,
    val doc_url: String,
    val height: Int,
    val image_url: String,
    val thumbnail_url: String,
    val width: Int
)