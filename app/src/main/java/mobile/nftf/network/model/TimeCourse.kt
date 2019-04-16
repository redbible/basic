package mobile.nftf.network.model

data class TimeCourse(
    val imageUrl: String,
    val kioskName: String,
    val optionGroupType: String,
    val prdId: Int,
    val tckId: Int,
    val ticketPrice: Int,
    val timeDesc: String,
    val currency: String,
    val count: Int,
    val multipleSelect: Boolean,
    val prices: List<Price>,
    val slotStocks: List<Slot>
) {

    data class Price(
        val discountBuyCount: Int,
        val discountDesc: String,
        val discountPrice: Int,
        val discountPriceYn: Boolean
    )

    data class Slot(
        val extId: String,
        val extProductId: String,
        val nfcName: String,
        val orderNo: Int,
        val slotName: String,
        val timeSlotStocks: List<TimeSlotStock>
    )

    data class TimeSlotStock(
        val extTimeId: String,
        val resvRate: Int,
        val stock: Int,
        val stockStatus: String,
        val time: String,
        val totalStock: Int
    )
}