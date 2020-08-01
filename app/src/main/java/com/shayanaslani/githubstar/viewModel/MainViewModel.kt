package com.shayanaslani.githubstar.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.shayanaslani.githubstar.model.GitHubRepo
import com.shayanaslani.githubstar.repository.Repository
import javax.inject.Inject

class MainViewModel @Inject constructor(val repository: Repository, application: Application) :
    AndroidViewModel(application) {
    fun getReposLiveData():LiveData<List<GitHubRepo>>{
        return repository.list
    }
    fun loadStarredRepos(userName: String){
        repository.loadStarredRepos(userName)
    }
}
