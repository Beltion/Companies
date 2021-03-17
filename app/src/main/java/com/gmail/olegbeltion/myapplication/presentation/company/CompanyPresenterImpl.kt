package com.gmail.olegbeltion.myapplication.presentation.company

import android.util.Log
import com.gmail.olegbeltion.core.business.entities.CompanyApiResultWrapper
import com.gmail.olegbeltion.core.business.entities.CompanyDeteil
import com.gmail.olegbeltion.myapplication.R
import com.gmail.olegbeltion.myapplication.business.logic.CompanyPresenter
import com.gmail.olegbeltion.myapplication.business.logic.CompanyView
import com.gmail.olegbeltion.myapplication.business.logic.HttpErrorStrCoder
import com.gmail.olegbeltion.myapplication.framework.Common
import com.tomtom.online.sdk.common.location.LatLng
import com.tomtom.online.sdk.map.*
import kotlinx.coroutines.*
import java.lang.ref.WeakReference

class CompanyPresenterImpl: CompanyPresenter {

    private val TAG = CompanyPresenterImpl::class.simpleName
    private var v: WeakReference<CompanyView>? = null
    private val model = CompanyModel()

    private val job = SupervisorJob()
    private val scopeMain
        get() = CoroutineScope(Dispatchers.Main + job)
    private val scopeIO
        get() = CoroutineScope(Dispatchers.IO + job)

    override fun initView(view: CompanyView) {
        v = WeakReference(view)
    }

    override fun onMapReady(tomtomMap: TomtomMap, position: LatLng) {
        val cameraPosition = CameraPosition.builder()
                .focusPosition(position)
                .zoom(13.0)
                .pitch(5.0)
                .bearing(MapConstants.ORIENTATION_NORTH.toDouble())
                .build()
        tomtomMap.centerOn(cameraPosition)

        val markerBuilder = MarkerBuilder(position)
                .iconAnchor(MarkerAnchor.Bottom)
                .decal(true)
        tomtomMap.addMarker(markerBuilder)
    }


    override fun onViewCreated() {
        v?.get()?.let { view ->
            val companyID = view.getCompanyID()
            if (companyID != 0) {
                Log.d(TAG, "Company id is good: $companyID")
                scopeMain.launch {
                    val companyResult = withContext(scopeIO.coroutineContext){
                        model.getCompany(companyID)
                    }

                    when(companyResult){
                        is CompanyApiResultWrapper.Success -> {
                            Log.d(TAG, "Get company is good")
                            val company = companyResult.company
                            if (company != null) {
                                val companyChecked = checkCompanyData(company)
                                withContext(scopeIO.coroutineContext){
                                    delay(1000)
                                }
                                view.setDataOnViews(companyChecked)

                            }
                            view.showContent()
                        }
                        is CompanyApiResultWrapper.ParseError -> {
                            Log.e(TAG,"Parse error")
                            Log.e(TAG,companyResult.errorBody)
                            view.showToast(
                                    view.getStringFromId(
                                            R.string.not_load
                                    )
                            )
                        }
                        is CompanyApiResultWrapper.NetworkError -> {
                            Log.e(TAG,"IOException")
                            view.showToast(view.getStringFromId(R.string.no_ethernet))
                            view.toCompanies()
                        }
                        is CompanyApiResultWrapper.Error -> {
                            Log.e(TAG, "Error with code:${companyResult.cod} mess:${companyResult.message}")
                            view.showToast(
                                    view.getStringFromId(
                                            HttpErrorStrCoder().getHttpErrorStrCode(code = companyResult.cod)
                                    )
                            )
                            view.toCompanies()
                        }
                        else -> {
                            Log.e(TAG,"Some error...")
                            view.showToast(
                                    view.getStringFromId(
                                            R.string.not_load
                                    )
                            )
                            view.toCompanies()
                        }
                    }
                }
            } else {
                Log.d(TAG, "Company id is bad")
            }
        }
    }

    private fun checkCompanyData(company: CompanyDeteil): CompanyDeteil {
        company.img = Common.BASE_URL + company.img
        Log.d(TAG,"IGM URL:${company.img}")
        if (company.name.isBlank()){
            company.name = "Имя не указано"
        }
        if (company.www.isBlank()){
            company.www = "Сайт не указан"
        }
        if (company.phone.isBlank()){
            company.phone = "телефон не указан"
        } else {
            company.phone = "т. ${company.phone}"
        }
        return company
    }

}