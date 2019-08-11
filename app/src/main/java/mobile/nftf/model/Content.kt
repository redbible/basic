package mobile.nftf.model

data class Content(
    val id: Int,
    val content: String,
    val done: Boolean,
    val seq: Int,
    val createdAt: String,
    val updatedAt: String
) {
    var isEdited: Boolean = false
}