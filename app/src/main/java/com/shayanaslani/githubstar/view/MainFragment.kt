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
    lateinit var mViewModel: MainViewModel
    @Inject
    lateinit var mAdapter: GitHubRepoAdapter
    lateinit var mBinding: MainFragmentBinding

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
        mBinding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)

        initRecyclerView()
        setListeners()
        setObservers()

        return mBinding.root
    }

    private fun setObservers() {
        mViewModel.getReposLiveData()
            .observe(viewLifecycleOwner, Observer<List<GitHubRepo>> { list: List<GitHubRepo> ->
                mAdapter.setRepoList(list)
            })
    }

    private fun setListeners() {
        mBinding.buttonSearch.setOnClickListener({
            mViewModel.loadStarredRepos(mBinding.editTextUsername.text.toString())
        })
    }

    fun initRecyclerView() {
        mBinding.recyclerViewRepos.layoutManager = LinearLayoutManager(context)
        mBinding.recyclerViewRepos.adapter = mAdapter
    }
}
