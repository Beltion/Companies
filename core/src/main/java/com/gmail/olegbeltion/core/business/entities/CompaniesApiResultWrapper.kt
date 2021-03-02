package com.gmail.olegbeltion.core.business.entities

import java.lang.reflect.Type

sealed class CompaniesApiResultWrapper<out Type> {
    object NetworkError: CompaniesApiResultWrapper<Nothing>()
    class Success(val companies: ArrayList<Company>): CompaniesApiResultWrapper<Type>()
    class Error(val cod: Int = 0, val message: String = ""): CompaniesApiResultWrapper<Nothing>()
}
