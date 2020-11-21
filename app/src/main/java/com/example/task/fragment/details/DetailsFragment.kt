package com.example.task.fragment.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.entity.localmodels.DataModel
import com.example.task.R
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailsFragment : Fragment() {

    private var data: DataModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            data = it.getParcelable(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initClickListeners()

    }

    private fun initData() {
        vTitle.text = data?.title
        vBody.text = data?.body
    }

    private fun initClickListeners() {
        activity?.let {
            it.findViewById<Toolbar>(R.id.toolbarId)
                .setNavigationOnClickListener { activity!!.onBackPressed() }

        }
    }


    companion object {
        private const val ARG_PARAM1 = "data"

        @JvmStatic
        fun newInstance(param1: DataModel) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM1, param1)
                }
            }
    }
}




