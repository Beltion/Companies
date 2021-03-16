package com.gmail.olegbeltion.myapplication.presentation.company

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.gmail.olegbeltion.core.business.entities.CompanyDeteil
import com.gmail.olegbeltion.myapplication.R
import com.gmail.olegbeltion.myapplication.business.logic.CompanyView
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.textview.MaterialTextView

class CompanyActivity :
        AppCompatActivity(),
        CompanyView {

    private lateinit var progressBarContainer: ConstraintLayout
    private lateinit var collapseToolbarLayout: CollapsingToolbarLayout
    private lateinit var toolBar: androidx.appcompat.widget.Toolbar
    private lateinit var img: ImageView

    private lateinit var www: MaterialTextView
    private lateinit var phone: MaterialTextView
    private lateinit var desc: MaterialTextView

    private lateinit var presenter: CompanyPresenterImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.company)
        initViews()
    }

    override fun onStart() {
        super.onStart()
        initLogic()
    }

    override fun toCompanies() {
        finish()
    }

    override fun getCompanyID() = intent.getIntExtra("id", 0)

    override fun setDataOnViews(company: CompanyDeteil) {

        Glide.with(this)
                .asDrawable()
                .load(company.img)
                .error(R.drawable.ic_baseline_corporate)
                .placeholder(R.drawable.ic_baseline_corporate)
                .override(200, 200)
                .fitCenter()
                .into(img)

        www.text = company.www
        phone.text = company.phone
        desc.text = company.description

        collapseToolbarLayout.title = company.name
    }

    override fun initViews() {
        progressBarContainer = findViewById(R.id.container_progress_bar)

        toolBar = findViewById(R.id.toolbar_company)
        collapseToolbarLayout = findViewById(R.id.collapsing_toolbar)

        img = findViewById(R.id.img_company)
        www = findViewById(R.id.www_company)
        phone = findViewById(R.id.phone_company)
        desc = findViewById(R.id.desc_company)

        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun initLogic() {
        presenter = CompanyPresenterImpl()
        presenter.initView(this)
        presenter.onViewCreated()
    }

    override fun showToast(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }

    override fun getStringFromId(id: Int) = getString(id)

    override fun showContent() {
        progressBarContainer.visibility = View.GONE
    }

    override fun hideContent() {
        progressBarContainer.visibility = View.VISIBLE
    }
}