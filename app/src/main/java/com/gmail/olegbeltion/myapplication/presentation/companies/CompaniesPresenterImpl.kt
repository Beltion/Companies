package com.gmail.olegbeltion.myapplication.presentation.companies

import android.util.Log
import com.gmail.olegbeltion.core.business.entities.CompaniesApiResultWrapper
import com.gmail.olegbeltion.myapplication.business.logic.CompaniesPresenter
import com.gmail.olegbeltion.myapplication.business.logic.CompaniesView
import kotlinx.coroutines.*
import java.lang.ref.WeakReference
import java.lang.reflect.Type

class CompaniesPresenterImpl : CompaniesPresenter {

    private val TAG = CompaniesPresenterImpl::class.simpleName
    private var v: WeakReference<CompaniesView>? = null
    private val model = CompaniesModel()

    private val job = SupervisorJob()
    private val scopeMain
        get() = CoroutineScope(Dispatchers.Main + job)
    private val scopeIO
        get() = CoroutineScope(Dispatchers.IO + job)


    override fun initView(view: CompaniesView) {
        v = WeakReference(view)
    }

    override fun onItemCLick(companyID: Int) {
        v?.get()?.startCompanyDeteil(companyID)
    }


    override fun onViewCreated() {
        v?.get()?.let { view ->
            scopeMain.launch {
                val companiesResult = withContext(scopeIO.coroutineContext){
                    delay(2000)
                    model.getCompanies()
                }
                onCompaniesResultAction(companiesResult)
                view.showContent()
            }


        }
    }

    private fun onCompaniesResultAction(companiesResult: CompaniesApiResultWrapper<Type>) {
        v?.get()?.let { view ->
            when(companiesResult){
                is CompaniesApiResultWrapper.Success -> {
                    Log.d(TAG, "Good request. Result is:")
                    Log.d(TAG, companiesResult.companies.toString())
                    view.initCompaniesRecyclerView(companiesResult.companies)
                    view.showContent()
                }
                is CompaniesApiResultWrapper.Error -> {
                    Log.d(TAG, "Bad request. Http error is:")
                    Log.e(TAG, "Cod: ${companiesResult.cod}, Msg: ${companiesResult.message}")
                }
                is CompaniesApiResultWrapper.NetworkError -> {
                    Log.e(TAG, "Bad request. Network error :(")
                }
                else -> {
                    Log.e(TAG, "Something wrong :(")
                    Log.e(TAG, companiesResult.toString())
                }
            }

        }
    }
}