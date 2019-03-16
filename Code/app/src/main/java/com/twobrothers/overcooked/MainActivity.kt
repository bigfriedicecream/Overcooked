package com.twobrothers.overcooked

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.twobrothers.overcooked.app.Navigation

import com.twobrothers.overcooked.views.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.layout_app, MainFragment())
                    .commitAllowingStateLoss()
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            this.finishAffinity()
            return
        }

        super.onBackPressed()
    }
}
