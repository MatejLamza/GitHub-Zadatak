package com.example.git_zadatak.view.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.git_zadatak.R
import com.example.git_zadatak.data.models.GitUser
import com.example.git_zadatak.data.models.Item
import com.example.git_zadatak.utils.MyConsts
import com.example.git_zadatak.utils.MyUtils
import com.example.git_zadatak.viewmodels.OwnerDetailsViewModel
import com.example.git_zadatak.viewmodels.factories.OwnerDetailsVMFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_owner_details.*
import javax.inject.Inject

class OwnerDetailsActivity:ScopedActivity() {

    @Inject
    lateinit var factory:OwnerDetailsVMFactory

    private lateinit var ownerDetailsVM:OwnerDetailsViewModel

    private lateinit var recivedProject: Item

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_owner_details)

        getDataFromBundle()

        ownerDetailsVM = ViewModelProviders.of(this,factory).get(OwnerDetailsViewModel::class.java)
        ownerDetailsVM.getCachedRepoOwner()


        ownerDetailsVM.liveRepoOwner.observe(this, Observer {
            initView(it)
            setImage(it.avatarUrl)
        })



        btn_owner_details.setOnClickListener {
            MyUtils.openWebBrowser(ownerDetailsVM.liveRepoOwner.value!!.htmlUrl,this)
        }
    }

    private fun setImage(imageURL:String){
        Glide.with(this)
            .load(imageURL)
            .into(iv_owner_details_avatar)
    }

    private fun getDataFromBundle(){
        if (intent.hasExtra(MyConsts.EXTRA_PROJECT_ITEM)){
            recivedProject = intent.getSerializableExtra(MyConsts.EXTRA_PROJECT_ITEM) as Item
        }
    }

    private fun initView(user:GitUser){
        tv_owner_details_name.text = user.name
        tv_owner_details_location.text = user.location
        tv_owner_details_email.text = user.email.toString()
        tv_owner_Details_company.text = user.company
        tv_owner_details_bio.text = user.bio
        tv_owner_details_followers.text = user.followers.toString()
    }
}