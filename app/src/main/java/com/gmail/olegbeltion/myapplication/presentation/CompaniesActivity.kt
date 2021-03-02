package com.gmail.olegbeltion.myapplication.presentation

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gmail.olegbeltion.core.business.entities.Company
import com.gmail.olegbeltion.myapplication.R
import com.gmail.olegbeltion.myapplication.business.CompaniesView

class CompaniesActivity:
    CompaniesView,
    AppCompatActivity() {

    lateinit var content: ConstraintLayout
    lateinit var progressBar: ProgressBar
    lateinit var rvCompanies: RecyclerView

    lateinit var presenter: CompaniesPresenterImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
    }

    override fun onStart() {
        super.onStart()
        initLogic()
    }

    override fun initCompaniesRecyclerView(companies: ArrayList<Company>) {
        TODO("Not yet implemented")
    }

    override fun startCompanyDeteil(companyId: Int) {
        TODO("Not yet implemented")
    }

    override fun initViews() {
        content = findViewById(R.id.content_companies)
        progressBar = findViewById(R.id.progress_bar)

        rvCompanies = findViewById(R.id.rv_companies)
        rvCompanies.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    override fun initLogic() {
        presenter = CompaniesPresenterImpl()
        presenter.initView(this)
        presenter.onViewCreated()
    }

    override fun showToast(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }

    override fun getStringFromId(id: Int) = getString(id)



    override fun showContent() {
        content.visibility = View.VISIBLE
        progressBar.visibility = View.INVISIBLE
    }

    override fun hideContent() {
        content.visibility = View.INVISIBLE
        progressBar.visibility = View.VISIBLE

    }
}