package mobile.nftf.model

data class Contents(
    val is_end: Boolean,
    val page: Int,
    var items: List<Item>
)