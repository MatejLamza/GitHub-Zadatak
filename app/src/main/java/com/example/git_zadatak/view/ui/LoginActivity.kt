package com.example.git_zadatak.view.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import com.example.git_zadatak.R
import com.example.git_zadatak.data.models.AcessToken
import com.example.git_zadatak.utils.MyConsts
import com.example.git_zadatak.utils.internal.ActivityNavigation
import com.example.git_zadatak.viewmodels.LoginViewModel
import com.example.git_zadatak.viewmodels.factories.LoginVMFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject
import javax.security.auth.callback.Callback
import kotlin.math.log

class LoginActivity:AppCompatActivity(),ActivityNavigation {

    @Inject
    lateinit var factory:LoginVMFactory

    private lateinit var loginVM:LoginViewModel

    override fun onStart() {
        super.onStart()
        loginVM.checkIfUserIsLoggedIn(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val spinner: ProgressBar = findViewById(R.id.load_spinner)

        loginVM = ViewModelProviders.of(this,factory).get(LoginViewModel::class.java)

        loginVM.startActivityForResultEvent.setEventReceiver(this,this)

        btn_google_login.setOnClickListener {
            loginVM.signInWithGoogle()
        }

        loginVM.spinner.observe(this) { value ->
            value.let { show ->
                spinner.visibility = if (show) View.VISIBLE else View.GONE
            }
        }

        loginVM.loginSuccess.observe(this, Observer {
            if (it) {
                loginVM.forwardToHomeScreen(this)
            }
        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        loginVM.onResultFromActivity(requestCode,data)
        super.onActivityResult(requestCode, resultCode, data)

    }


}