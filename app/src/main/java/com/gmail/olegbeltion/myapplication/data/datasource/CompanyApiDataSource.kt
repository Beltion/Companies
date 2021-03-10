package com.gmail.olegbeltion.myapplication.data.datasource

import com.gmail.olegbeltion.core.business.entities.CompanyApiResultWrapper
import com.gmail.olegbeltion.core.business.entities.CompanyDeteil
import com.gmail.olegbeltion.core.data.datasource.CompanyDataSource
import com.gmail.olegbeltion.myapplication.framework.Common
import retrofit2.HttpException
import java.io.IOException
import java.lang.reflect.Type

class CompanyApiDataSource: CompanyDataSource {
    override suspend fun getCompany(companyID: Int): CompanyApiResultWrapper<Type> {
        return try {
            val company: CompanyDeteil = Common.retrofitCompanies.getCompany(companyID)[0]
            CompanyApiResultWrapper.Success(company)
        } catch (t: Throwable) {
            t.printStackTrace()
            when(t){
                is IOException -> CompanyApiResultWrapper.NetworkError
                is HttpException -> CompanyApiResultWrapper.Error(t.code(), t.message())
                else -> {
                    CompanyApiResultWrapper.Error(message = t.message ?: "")
                }
            }
        }
    }
}