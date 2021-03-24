package com.gmail.olegbeltion.myapplication.business.logic

import com.gmail.olegbeltion.core.business.entities.CompanyDeteil
import com.gmail.olegbeltion.core.business.logic.BasePresenter
import com.gmail.olegbeltion.core.business.logic.BaseView
import com.tomtom.online.sdk.common.location.LatLng
import com.tomtom.online.sdk.map.TomtomMap


interface CompanyPresenter: BasePresenter {
    fun initView(view: CompanyView)
    fun onMapReady(tomtomMap: TomtomMap, position: LatLng)
}

interface CompanyView: BaseView {
    fun toCompanies()
    fun getCompanyID(): Int
    fun setDataOnViews(company: CompanyDeteil)
}