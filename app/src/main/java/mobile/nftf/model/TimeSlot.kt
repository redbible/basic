package mobile.nftf.model

import mobile.nftf.ui.TimeSlotAdapter

data class TimeSlot(
    val viewType: TimeSlotAdapter.ViewType,
    val time: String,
    val extId: String = "",
    val extProductId: String = "",
    val extTimeId: String = "",
    val name: String = "",
    var isEnabled: Boolean = false,
    var isSelected: Boolean = false
)