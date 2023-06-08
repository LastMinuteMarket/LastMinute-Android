package com.lastminute

import android.app.Application
import android.content.Context
import com.lastminute.ui.main.MainViewModel
import com.lastminute.ui.search.SearchViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class LastMinuteApplication : Application() {

    companion object {
        var instance: LastMinuteApplication? = null
        fun context(): Context = instance!!.applicationContext
    }

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@LastMinuteApplication)
            modules(viewModelModule)
        }
    }

    private val viewModelModule = module {
        viewModel { MainViewModel() }
        viewModel { SearchViewModel()}
    }
}