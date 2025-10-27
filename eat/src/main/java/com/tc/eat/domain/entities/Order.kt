package com.tc.eat.domain.entities

class Order(
    val deliveryAddress : String,
    val foodCart : MutableList<MenuItem> = mutableListOf<MenuItem>()
){
    fun totalCost(deliveryFee : Float) : Float {
        return (subTotal() + deliveryFee)
    }
    fun subTotal() : Float {
        var subtotal = 0f
        foodCart.forEach { foodItem ->
            subtotal += foodItem.price
        }
        return subtotal
    }
}