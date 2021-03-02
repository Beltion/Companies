package com.gmail.olegbeltion.core.business.logic

interface BaseView {
    fun initViews()
    fun initLogic()
    fun showToast(s: String)
    fun getStringFromId(id: Int)
    fun showContent()
    fun hideContent()
}