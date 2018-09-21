package com.bigfriedicecream.recipes

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.bigfriedicecream.recipes.views.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.layout_app, MainFragment())
                    .commit()
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
