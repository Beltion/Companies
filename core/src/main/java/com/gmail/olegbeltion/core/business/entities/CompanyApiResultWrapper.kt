package com.gmail.olegbeltion.core.business.entities

import java.lang.reflect.Type

sealed class CompanyApiResultWrapper<out Type> {
    object NetworkError: CompanyApiResultWrapper<Nothing>()
    class Success(val company: CompanyDeteil): CompanyApiResultWrapper<Type>()
    class Error(val cod: Int = 0, val message: String = ""): CompanyApiResultWrapper<Nothing>()
}