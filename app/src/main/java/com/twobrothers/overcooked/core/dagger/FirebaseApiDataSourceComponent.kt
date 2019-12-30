package com.twobrothers.overcooked.core.dagger

import com.twobrothers.overcooked.core.datasource.FirebaseApiDataSource
import dagger.Component

@Component
interface FirebaseApiDataSourceComponent {

    fun getDataSource(): FirebaseApiDataSource
}