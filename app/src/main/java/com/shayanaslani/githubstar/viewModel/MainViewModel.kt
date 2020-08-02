package com.shayanaslani.githubstar.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.shayanaslani.githubstar.model.GitHubRepo
import com.shayanaslani.githubstar.repository.Repository
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: Repository, application: Application) :
    AndroidViewModel(application) {
    val starredRepo : LiveData<List<GitHubRepo>> = repository.starredRepos

    fun loadStarredRepos(userName: String) {
        viewModelScope.launch { repository.loadStarredRepos(userName) }
    }
}
