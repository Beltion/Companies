package com.gmail.olegbeltion.core.usecase

import com.gmail.olegbeltion.core.business.entities.CompaniesApiResultWrapper
import com.gmail.olegbeltion.core.data.repositories.CompaniesRepository
import java.lang.reflect.Type

class GetCompanies(private val repository: CompaniesRepository) {

    suspend fun getCompanies(): CompaniesApiResultWrapper<Type> {
        return repository.getCompanies()
    }

}