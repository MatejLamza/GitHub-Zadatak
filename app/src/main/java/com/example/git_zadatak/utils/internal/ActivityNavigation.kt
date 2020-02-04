package com.example.git_zadatak.utils.internal

import android.content.Intent

interface ActivityNavigation {
    fun startActivityForResult(intent: Intent?, requestCode: Int)
}