package com.gmail.olegbeltion.myapplication.framework

import com.gmail.olegbeltion.core.business.entities.Company
import com.gmail.olegbeltion.core.business.entities.CompanyDeteil
import okhttp3.Request
import okhttp3.ResponseBody
import okio.Timeout
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface CompaniesApi {
    @GET("test.php")
    suspend fun getCompanies(
    ): ArrayList<Company>

    @GET("test.php")
    suspend fun getCompany(
            @Query("id") id: Int
    ): Response<ArrayList<CompanyDeteil>>
}

object RetrofitClients {
    private var retrofit: Retrofit? = null
    fun getRetrofit(baseUrl: String): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
        return retrofit!!
    }
}

object Common {
    const val BASE_URL = "https://lifehack.studio/test_task/"
    val retrofitCompanies: CompaniesApi
        get() = RetrofitClients.getRetrofit(BASE_URL).create(CompaniesApi::class.java)
}

