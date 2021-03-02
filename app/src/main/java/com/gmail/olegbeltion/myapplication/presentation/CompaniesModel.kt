package com.gmail.olegbeltion.myapplication.presentation

import com.gmail.olegbeltion.core.business.entities.CompaniesApiResultWrapper
import java.lang.reflect.Type

class CompaniesModel {

    suspend fun getCompanies() : CompaniesApiResultWrapper<Type> {
//        TODO GetUseCase
        return CompaniesApiResultWrapper.NetworkError
    }

}