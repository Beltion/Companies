package com.gmail.olegbeltion.myapplication.data.datasource

import com.gmail.olegbeltion.core.business.entities.CompanyApiResultWrapper
import com.gmail.olegbeltion.core.data.datasource.CompanyDataSource
import com.gmail.olegbeltion.myapplication.framework.Common
import com.google.gson.stream.MalformedJsonException
import retrofit2.*
import java.io.IOException
import java.lang.reflect.Type

class CompanyApiDataSource : CompanyDataSource {
    override suspend fun getCompany(companyID: Int): CompanyApiResultWrapper<Type> {
        return try {
            val response = Common.retrofitCompanies.getCompany(companyID)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    CompanyApiResultWrapper.Success(body[0])
                } else {
                    CompanyApiResultWrapper.NetworkError
                }
            } else {
                val errorBody = response.errorBody()
                CompanyApiResultWrapper.ParseError(errorBody = errorBody.toString())
            }
        } catch (t: Throwable) {
            t.printStackTrace()
            when (t) {
                is MalformedJsonException -> CompanyApiResultWrapper.ParseError("Error parsing")
                is IOException -> CompanyApiResultWrapper.NetworkError
                is HttpException -> CompanyApiResultWrapper.Error(t.code(), t.message())
                else -> {
                    CompanyApiResultWrapper.Error(message = t.message ?: "")
                }
            }
        }
    }
}