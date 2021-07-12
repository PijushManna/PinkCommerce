package com.blogspot.pinkdelivery.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blogspot.pinkdelivery.R

class AllFragment : Fragment() {

    private val viewModel: AllViewModel by lazy {
        ViewModelProvider(this).get(AllViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.all_fragment, container, false)
        val rcrItems:RecyclerView = v.findViewById(R.id.rcr_items)

        return v
    }

}