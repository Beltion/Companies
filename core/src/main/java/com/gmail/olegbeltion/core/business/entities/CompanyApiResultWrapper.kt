package com.gmail.olegbeltion.core.business.entities

import java.lang.reflect.Type

sealed class CompanyApiResultWrapper<out Type> {
    object NetworkError: CompanyApiResultWrapper<Nothing>()
    data class ParseError(val errorBody: String): CompanyApiResultWrapper<Type>()
    data class Success(val company: CompanyDeteil?): CompanyApiResultWrapper<Type>()
    data class Error(val cod: Int = 0, val message: String = ""): CompanyApiResultWrapper<Nothing>()
}

