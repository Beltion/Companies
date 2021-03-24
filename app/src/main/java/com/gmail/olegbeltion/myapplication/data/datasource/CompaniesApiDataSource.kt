package com.gmail.olegbeltion.myapplication.data.datasource

import com.gmail.olegbeltion.core.business.entities.CompaniesApiResultWrapper
import com.gmail.olegbeltion.core.business.entities.Company
import com.gmail.olegbeltion.core.data.datasource.CompaniesDataSource
import com.gmail.olegbeltion.myapplication.framework.Common
import retrofit2.HttpException
import java.io.IOException
import java.lang.reflect.Type

class CompaniesApiDataSource: CompaniesDataSource {
    override suspend fun getCompanies(): CompaniesApiResultWrapper<Type> {
        return try {
            val companies: ArrayList<Company> = Common.retrofitCompanies.getCompanies()
            CompaniesApiResultWrapper.Success(companies)
        } catch (t: Throwable){
            t.printStackTrace()
            when(t){
                is IOException -> {
                    CompaniesApiResultWrapper.NetworkError}
                is HttpException -> {
                    CompaniesApiResultWrapper.Error(t.code(), t.message())
                }
                else -> {
                    CompaniesApiResultWrapper.Error(message = t.message ?: "")
                }
            }
        }
    }
}