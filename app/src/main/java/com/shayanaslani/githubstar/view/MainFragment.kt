package com.shayanaslani.githubstar.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shayanaslani.githubstar.utils.MyApplication
import com.shayanaslani.githubstar.viewModel.MainViewModel
import com.shayanaslani.githubstar.R
import javax.inject.Inject


class MainFragment : Fragment() {

    @Inject
    lateinit var mViewModel: MainViewModel

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
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        return view
    }
}
