package mobile.nftf.model

import mobile.nftf.network.model.ClipKakao
import mobile.nftf.network.model.ImageKakao

data class Item(
    val thumbnail: String = "https://ssl.pstatic.net/tveta/libs/1259/1259872/be9291d99a38625a9722_20191002160658234.png",
    val dateMilliSec: Long = 0L,
    val image: ImageKakao? = null,
    val clip: ClipKakao? = null
)