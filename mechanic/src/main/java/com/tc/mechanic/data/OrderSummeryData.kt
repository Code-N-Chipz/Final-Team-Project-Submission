package com.tc.mechanic.data

data class OrderSummaryData(
    val mechanicName: String = "Jenny Jones",
    val role: String = "Handyman",
    val date: String = "20 March, Thu - 14h",
    val location: String = "28 Broad Street Johannesburg",
    val ratePerHour: Float = 15f,
    val hours: Int = 3,
    val deliveryFee: Float = 0f
) {
    val subtotal: Float get() = ratePerHour * hours
    val total: Float get() = subtotal + deliveryFee
}