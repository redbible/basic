package mobile.nftf.model

import mobile.nftf.network.enumeration.Type
import mobile.nftf.network.model.ClipKakao
import mobile.nftf.network.model.ImageKakao

data class Item(
    val thumbnail: String,
    val dateMilliSec: Long,
    val image: ImageKakao? = null,
    val clip: ClipKakao? = null
) {
    fun getItem() = image ?: clip!!

    fun getType() = if (image != null) Type.image else Type.clip
}