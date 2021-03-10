package com.gmail.olegbeltion.myapplication.presentation.company

import com.gmail.olegbeltion.core.business.entities.CompanyApiResultWrapper
import com.gmail.olegbeltion.core.usecase.GetCompanyUseCase
import com.gmail.olegbeltion.myapplication.data.datasource.CompanyApiDataSource
import com.gmail.olegbeltion.myapplication.data.repositories.CompanyRepositoryImpl
import java.lang.reflect.Type

class CompanyModel {

    suspend fun getCompany(companyID: Int): CompanyApiResultWrapper<Type>{
        val getCompanyUseCase = GetCompanyUseCase(CompanyRepositoryImpl(CompanyApiDataSource()))
        return getCompanyUseCase.getCompany(companyID)
    }

}