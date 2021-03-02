package com.gmail.olegbeltion.myapplication.presentation

import com.gmail.olegbeltion.myapplication.business.CompaniesPresenter
import com.gmail.olegbeltion.myapplication.business.CompaniesView
import java.lang.ref.WeakReference

class CompaniesPresenterImpl : CompaniesPresenter {

    private val TAG = CompaniesPresenterImpl::class.simpleName
    private var v: WeakReference<CompaniesView>? = null
    private val model = CompaniesModel()


    override fun initView(view: CompaniesView) {
        v = WeakReference(view)
    }

    override fun onViewCreated() {
        v?.get()?.let { view ->
            view.showContent()
        }
    }
}