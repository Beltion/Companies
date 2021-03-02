package com.gmail.olegbeltion.myapplication.business

import com.gmail.olegbeltion.core.business.entities.Company
import com.gmail.olegbeltion.core.business.logic.BasePresenter
import com.gmail.olegbeltion.core.business.logic.BaseView

interface CompaniesPresenter: BasePresenter {
    fun initView(view: CompaniesView)
}

interface CompaniesView: BaseView {
    fun initCompaniesRecyclerView(companies: ArrayList<Company>)
    fun startCompanyDeteil(companyId: Int)
}