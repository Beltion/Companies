package com.gmail.olegbeltion.core.business.entities

data class CompanyDeteil(
    val id: Int = 0,
    val name: String = "",
    val img: String = "",
    val description: String = "",
    val lat: Double = 0.0,
    val lon: Double = 0.0,
    val www: String = "",
    val phone: String = ""
)
