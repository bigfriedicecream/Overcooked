package com.bigfriedicecream.recipes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bigfriedicecream.recipes.views.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.layout_app, new MainFragment())
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            this.finishAffinity();
            return;
        }

        super.onBackPressed();
    }
}
