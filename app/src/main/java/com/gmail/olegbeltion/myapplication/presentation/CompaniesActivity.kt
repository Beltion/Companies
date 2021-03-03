package com.gmail.olegbeltion.myapplication.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gmail.olegbeltion.core.business.entities.Company
import com.gmail.olegbeltion.myapplication.R
import com.gmail.olegbeltion.myapplication.business.CompaniesView
import com.gmail.olegbeltion.myapplication.business.adapters.CompaniesRvAdapter

class CompaniesActivity :
        CompaniesView,
        CompaniesRvAdapter.OnCompaniesRvAdapterListener,
        AppCompatActivity() {

    lateinit var content: ConstraintLayout
    lateinit var progressBarContainer: ConstraintLayout
    lateinit var rvCompanies: RecyclerView

    lateinit var presenter: CompaniesPresenterImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.companies)
        initViews()
    }

    override fun onStart() {
        super.onStart()
        initLogic()
    }

    override fun initCompaniesRecyclerView(companies: ArrayList<Company>) {
        val adapter = CompaniesRvAdapter(companies, this)
        rvCompanies.adapter = adapter
    }

    override fun startCompanyDeteil(companyId: Int) {
        TODO("Not yet implemented")
    }

    override fun initViews() {
        content = findViewById(R.id.content_companies)
        progressBarContainer = findViewById(R.id.container_progress_bar)

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
        progressBarContainer.visibility = View.INVISIBLE
    }

    override fun hideContent() {
        content.visibility = View.INVISIBLE
        progressBarContainer.visibility = View.VISIBLE

    }

    override fun onItemClick() {
        TODO("Not yet implemented")
    }

}