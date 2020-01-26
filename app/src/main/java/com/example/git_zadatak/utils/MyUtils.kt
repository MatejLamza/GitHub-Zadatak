package com.example.git_zadatak.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.inputmethod.InputMethodManager

class MyUtils {

    companion object {

        fun hideKeyboard(activity: Activity) {
            val inputManager =
                activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            var view = activity.currentFocus

            if (view == null) {
                view = View(activity)
            }

            inputManager.hideSoftInputFromWindow(view.windowToken, 0)
            view.clearFocus()
        }

        fun openWebBrowser(repoURL: String,context: Context) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(repoURL))
            context.startActivity(intent)
        }



    }
}