package com.shayanaslani.githubstar.component

import com.shayanaslani.githubstar.modules.AppModule
import com.shayanaslani.githubstar.modules.RepositoryModule
import com.shayanaslani.githubstar.modules.RetrofitModule
import com.shayanaslani.githubstar.view.MainFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class , RetrofitModule::class , RepositoryModule::class])
interface AppComponent{
    fun inject(mainFragment: MainFragment)
}