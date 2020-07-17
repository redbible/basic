package mobile.nftf.network.model

import java.util.*

data class ClipKakao(
    val author: String,
    val datetime: Date,
    val play_time: Int,
    val thumbnail: String,
    val title: String,
    val url: String
)