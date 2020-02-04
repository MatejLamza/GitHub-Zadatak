package com.example.git_zadatak.view.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.git_zadatak.R
import com.example.git_zadatak.data.models.UserProfile
import com.example.git_zadatak.viewmodels.UserProfileViewModel
import com.example.git_zadatak.viewmodels.factories.UserProfileVMFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_owner_details.*
import kotlinx.android.synthetic.main.activity_user_info.*
import javax.inject.Inject

class UserProfileActivity:AppCompatActivity() {

    @Inject
    lateinit var factory:UserProfileVMFactory

    private lateinit var userProfileVM: UserProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        userProfileVM = ViewModelProviders.of(this,factory).get(UserProfileViewModel::class.java)

        userProfileVM.getCachedUserProfile()

        userProfileVM.liveUserProfile.observe(this, Observer {
            initView(it)
            setImage(it.photoURL!!)
        })

        btn_log_out.setOnClickListener {
            Toast.makeText(this,"Logging off",Toast.LENGTH_SHORT).show()
            userProfileVM.googleSignOut()
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setImage(imageURL:String){
        Glide.with(this)
            .load(imageURL)
            .into(iv_user_info_avatar)
    }

    private fun initView(userProfile: UserProfile){
        tv_user_info_email.text = userProfile.email
        tv_user_info_name.text = userProfile.name
    }
}