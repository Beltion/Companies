package com.gmail.olegbeltion.myapplication.data.repositories

import com.gmail.olegbeltion.core.business.entities.CompaniesApiResultWrapper
import com.gmail.olegbeltion.core.data.repositories.CompaniesRepository
import com.gmail.olegbeltion.myapplication.data.datasource.CompaniesApiDataSource
import java.lang.reflect.Type

class CompaniesRepositoryImpl(private val ds: CompaniesApiDataSource): CompaniesRepository {
    override suspend fun getCompanies(): CompaniesApiResultWrapper<Type>
        = ds.getCompanies()
}