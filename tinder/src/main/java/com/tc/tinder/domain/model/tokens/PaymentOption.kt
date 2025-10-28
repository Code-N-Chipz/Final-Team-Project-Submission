package com.tc.tinder.domain.model.tokens

data class PaymentOption(
    val id: String,
    val quantity: String,      // e.g., "1" or "5" or "10"
    val itemName: String,      // e.g., "Boosts" / "Likes"
    val price: String,         // e.g., "3.99"
    val onSale: Boolean = false,
    val discountLabel: String = "SAVE 25%"
)


val boostOptions = listOf(
    PaymentOption(id = "1", quantity = "1", itemName = "Boosts", price = "3.99"),
    PaymentOption(id = "5",  quantity = "5",  itemName = "Boosts", price = "5.99", onSale = true),
    PaymentOption(id = "10", quantity = "10", itemName = "Boosts", price = "6.99")
)

val likeOptions = listOf(
    PaymentOption(id = "1", quantity = "1", itemName = "Likes", price = "3.99"),
    PaymentOption(id = "5",  quantity = "5",  itemName = "Likes", price = "5.99", onSale = true),
    PaymentOption(id = "10", quantity = "10", itemName = "Likes", price = "6.99")
)


val superLikeOptions = listOf(
    PaymentOption(id = "1", quantity = "1", itemName = "SuperLikes", price = "3.99"),
    PaymentOption(id = "5",  quantity = "5",  itemName = "SuperLikes", price = "5.99", onSale = true),
    PaymentOption(id = "10", quantity = "10", itemName = "SuperLikes", price = "6.99")
)

