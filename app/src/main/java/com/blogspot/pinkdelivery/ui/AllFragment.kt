package com.blogspot.pinkdelivery.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blogspot.pinkdelivery.R
import com.blogspot.pinkdelivery.databinding.AllFragmentBinding
import com.blogspot.pinkdelivery.models.PinkItems

class AllFragment : Fragment() {

    private val viewModel: AllViewModel by lazy {
        ViewModelProvider(this).get(AllViewModel::class.java)
    }
    private lateinit var adapter: AllItemsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = AllFragmentBinding.inflate(inflater)
        adapter = AllItemsAdapter(viewModel)
        binding.rcrItems.adapter = adapter
        binding.lifecycleOwner = this
        binding.model = viewModel
        adapter.notifyDataSetChanged()

        viewModel.fetchData(requireContext())
        return binding.root
    }

}