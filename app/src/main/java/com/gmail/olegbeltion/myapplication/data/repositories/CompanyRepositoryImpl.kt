package com.gmail.olegbeltion.myapplication.data.repositories

import com.gmail.olegbeltion.core.business.entities.CompanyApiResultWrapper
import com.gmail.olegbeltion.core.data.datasource.CompanyDataSource
import com.gmail.olegbeltion.core.data.repositories.CompanyRepository
import java.lang.reflect.Type

class CompanyRepositoryImpl(private val dataSource: CompanyDataSource): CompanyRepository {
    override suspend fun getCompany(companyID: Int): CompanyApiResultWrapper<Type> {
        return dataSource.getCompany(companyID)
    }
}