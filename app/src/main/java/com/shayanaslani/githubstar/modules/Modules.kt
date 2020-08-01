package com.shayanaslani.githubstar.modules

import android.app.Application
import android.content.Context
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.shayanaslani.githubstar.network.GitHubService
import com.shayanaslani.githubstar.repository.Repository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RetrofitModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val gson = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
        return Retrofit.Builder()
            .baseUrl(GITHUB_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideGitHubService(retrofit: Retrofit): GitHubService {
        return retrofit.create(GitHubService::class.java)
    }

    companion object {
        private const val GITHUB_BASE_URL = "https://api.github.com/"
    }
}

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideRepository(gitHubService: GitHubService): Repository {
        return Repository(gitHubService)
    }
}

@Module
class AppModule(val application: Application) {
    @Provides
    @Singleton
    fun provideApplication(): Application {
        return application
    }
}





