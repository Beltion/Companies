package com.gmail.olegbeltion.myapplication.presentation.company

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.gmail.olegbeltion.core.business.entities.CompanyDeteil
import com.gmail.olegbeltion.myapplication.R
import com.gmail.olegbeltion.myapplication.business.logic.CompanyView
import com.google.android.material.textview.MaterialTextView

class CompanyActivity:
        AppCompatActivity(),
        CompanyView
{

    private lateinit var contentContainer: ConstraintLayout
    private lateinit var progressBarContainer: ConstraintLayout
    private lateinit var img: ImageView
    private lateinit var name: MaterialTextView
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
                .override(200,200)
                .fitCenter()
                .into(img)

        name.text = company.name
        www.text = company.www
        phone.text = company.phone
        desc.text = company.description
    }

    override fun initViews() {
//        contentContainer = findViewById(R.id.content_company)
        progressBarContainer = findViewById(R.id.container_progress_bar)

        img = findViewById(R.id.img_company)
        name = findViewById(R.id.name_company)
        www = findViewById(R.id.www_company)
        phone = findViewById(R.id.phone_company)
        desc = findViewById(R.id.desc_company)

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
//        contentContainer.visibility = View.VISIBLE
        progressBarContainer.visibility = View.GONE
    }

    override fun hideContent() {
//        contentContainer.visibility = View.INVISIBLE
        progressBarContainer.visibility = View.VISIBLE
    }
}