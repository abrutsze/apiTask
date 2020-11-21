package com.example.task.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.task.R
import com.example.task.fragment.details.DetailsFragment
import com.example.task.fragment.home.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*

import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initViewClick()
    }


    private fun initViewClick() {
        btGetData.setOnClickListener {
            homeViewModel.getDataList(edInputNumber.text.toString())
        }
    }

    private fun initViewModel() {
        homeViewModel.getDataModelDataList.observe(viewLifecycleOwner, {
            val specialtyFragment = DetailsFragment.newInstance(it)
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.rootFragment, specialtyFragment)
                addToBackStack(null)
                commit()
            }
        })
        homeViewModel.loadingData.observe(viewLifecycleOwner, {
            if (it)
                vLoadingData.visibility = View.VISIBLE
            else
                vLoadingData.visibility = View.GONE
        })
        homeViewModel.errorNullData.observe(viewLifecycleOwner, {
            context?.run {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
            vLoadingData.visibility = View.GONE
        })
    }
}