package com.gmail.olegbeltion.myapplication.business.logic

import android.graphics.drawable.Drawable
import com.gmail.olegbeltion.core.business.entities.CompanyDeteil
import com.gmail.olegbeltion.core.business.logic.BasePresenter
import com.gmail.olegbeltion.core.business.logic.BaseView


interface CompanyPresenter: BasePresenter {
    fun initView(view: CompanyView)
}

interface CompanyView: BaseView {
    fun toCompanies()
    fun getCompanyID(): Int
    fun setDataOnViews(company: CompanyDeteil)
}