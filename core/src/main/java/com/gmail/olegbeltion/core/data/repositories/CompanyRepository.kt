package com.gmail.olegbeltion.core.data.repositories

import com.gmail.olegbeltion.core.business.entities.CompanyApiResultWrapper
import java.lang.reflect.Type

interface CompanyRepository {
    suspend fun getCompany(companyID: Int): CompanyApiResultWrapper<Type>
}