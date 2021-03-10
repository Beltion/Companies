package com.gmail.olegbeltion.core.data.datasource

import com.gmail.olegbeltion.core.business.entities.CompanyApiResultWrapper
import java.lang.reflect.Type

interface CompanyDataSource {
    suspend fun getCompany(companyID: Int): CompanyApiResultWrapper<Type>
}