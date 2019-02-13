package com.twobrothers.overcooked.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.twobrothers.overcooked.R
import com.twobrothers.overcooked.interfaces.IRecipeListContract
import com.twobrothers.overcooked.models.RecipeModel
import com.twobrothers.overcooked.presenters.RecipeListPresenter
import com.google.gson.Gson
import com.twobrothers.overcooked.BuildConfig
import io.reactivex.plugins.RxJavaPlugins
import kotlinx.android.synthetic.main.fragment_recipe_list.view.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

import java.util.HashMap



class RecipeListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.API_ENDPOINT)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        return inflater.inflate(R.layout.fragment_recipe_list, container, false)
    }
}
