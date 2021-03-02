package com.gmail.olegbeltion.myapplication.presentation

import android.util.Log
import com.gmail.olegbeltion.core.business.entities.CompaniesApiResultWrapper
import com.gmail.olegbeltion.myapplication.business.CompaniesPresenter
import com.gmail.olegbeltion.myapplication.business.CompaniesView
import kotlinx.coroutines.*
import java.lang.ref.WeakReference

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

    override fun onViewCreated() {
        v?.get()?.let { view ->
            scopeMain.launch {
                view.showContent()
                val companiesResult = withContext(scopeIO.coroutineContext){
                    model.getCompanies()
                }
                when(companiesResult){
                    is CompaniesApiResultWrapper.Success -> {
                        Log.d(TAG, "Good request. Result is:")
                        Log.d(TAG, companiesResult.companies.toString())
                    }
                    is CompaniesApiResultWrapper.Error -> {
                        Log.d(TAG, "Bad request. Http error is:")
                        Log.e(TAG, "Cod: ${companiesResult.cod}, Msg: ${companiesResult.message}")
                    }
                    is CompaniesApiResultWrapper.NetworkError -> {
                        Log.d(TAG, "Bad request. Network error :(")
                    }
                }
            }


        }
    }
}