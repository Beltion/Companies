package com.gmail.olegbeltion.core.usecase

import com.gmail.olegbeltion.core.business.entities.CompanyApiResultWrapper
import com.gmail.olegbeltion.core.data.repositories.CompanyRepository
import java.lang.reflect.Type

class GetCompanyUseCase(private val repository: CompanyRepository) {
    suspend fun getCompany(companyID: Int): CompanyApiResultWrapper<Type> {
        return repository.getCompany(companyID)
    }
}