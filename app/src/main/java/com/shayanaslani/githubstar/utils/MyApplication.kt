package com.shayanaslani.githubstar.utils

import android.app.Application
import com.shayanaslani.githubstar.component.AppComponent
import com.shayanaslani.githubstar.DaggerAppComponent
import com.shayanaslani.githubstar.modules.AppModule
import com.shayanaslani.githubstar.modules.RepositoryModule
import com.shayanaslani.githubstar.modules.RetrofitModule

class MyApplication : Application(){


    companion object{
        private var component: AppComponent? = null
        fun getComponent(): AppComponent? {
            return component
        }
    }

    override fun onCreate() {
        super.onCreate()
        component = buildComponent()
    }

    private fun buildComponent(): AppComponent? {

        return DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .retrofitModule(RetrofitModule())
            .repositoryModule(RepositoryModule())
            .build()
    }
}