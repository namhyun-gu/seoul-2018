package com.namhyun.seoul2018

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_info.*

class InfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        initViews()
    }

    private fun initViews() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        tv_app_version.text = getAppVersion()
    }

    private fun getAppVersion() = try {
        packageManager.getPackageInfo(packageName, 0).versionName
    } catch (e: Exception) {
        "none"
    }
}