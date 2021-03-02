package com.gmail.olegbeltion.core.data.datasource

import com.gmail.olegbeltion.core.business.entities.CompaniesApiResultWrapper
import java.lang.reflect.Type

interface CompaniesDataSource {
    suspend fun getCompanies(): CompaniesApiResultWrapper<Type>
}