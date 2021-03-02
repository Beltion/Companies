package com.gmail.olegbeltion.core.data.repositories

import com.gmail.olegbeltion.core.business.entities.CompaniesApiResultWrapper
import java.lang.reflect.Type

interface CompaniesRepository {
     suspend fun getCompanies(): CompaniesApiResultWrapper<Type>
}