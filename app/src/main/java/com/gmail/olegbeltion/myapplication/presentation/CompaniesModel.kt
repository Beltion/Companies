package com.gmail.olegbeltion.myapplication.presentation

import com.gmail.olegbeltion.core.business.entities.CompaniesApiResultWrapper
import com.gmail.olegbeltion.core.usecase.GetCompaniesUseCase
import com.gmail.olegbeltion.myapplication.data.datasource.CompaniesApiDataSource
import com.gmail.olegbeltion.myapplication.data.repositories.CompaniesRepositoryImpl
import java.lang.reflect.Type

class CompaniesModel {

    suspend fun getCompanies() : CompaniesApiResultWrapper<Type> {
        val getCompaniesUseCase = GetCompaniesUseCase(CompaniesRepositoryImpl(CompaniesApiDataSource()))
        return getCompaniesUseCase.getCompanies()
    }

}