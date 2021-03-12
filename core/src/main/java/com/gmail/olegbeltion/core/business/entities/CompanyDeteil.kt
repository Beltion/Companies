package com.gmail.olegbeltion.core.business.entities

data class CompanyDeteil(
    val id: Int = 0,
    var name: String = "",
    var img: String = "",
    val description: String = "",
    val lat: Double = 0.0,
    val lon: Double = 0.0,
    var www: String = "",
    var phone: String = ""
)
