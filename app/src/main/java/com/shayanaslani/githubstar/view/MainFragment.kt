package com.shayanaslani.githubstar.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.shayanaslani.githubstar.adapter.GitHubRepoAdapter
import com.shayanaslani.githubstar.utils.MyApplication
import com.shayanaslani.githubstar.viewModel.MainViewModel
import com.shayanaslani.githubstar.R
import com.shayanaslani.githubstar.databinding.MainFragmentBinding
import com.shayanaslani.githubstar.model.GitHubRepo
import javax.inject.Inject


class MainFragment : Fragment() {

    @Inject
    lateinit var viewModel: MainViewModel
    @Inject
    lateinit var gitHubRepoAdapter: GitHubRepoAdapter
    lateinit var binding: MainFragmentBinding

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        MyApplication.getComponent()?.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)

        initRecyclerView()
        setListeners()
        setObservers()

        return binding.root
    }

    private fun setObservers() {
        viewModel.starredRepo
            .observe(viewLifecycleOwner, Observer<List<GitHubRepo>> { list: List<GitHubRepo> ->
                gitHubRepoAdapter.setRepoList(list)
            })
    }

    private fun setListeners() {
        binding.buttonSearch.setOnClickListener {
            viewModel.loadStarredRepos(binding.editTextUsername.text.toString())
        }
    }

    fun initRecyclerView() {
        binding.recyclerViewRepos.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewRepos.adapter = gitHubRepoAdapter
    }
}
