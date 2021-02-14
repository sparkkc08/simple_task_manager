package com.example.taskmanager.ui.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.databinding.MainFragmentBinding
import com.example.taskmanager.ui.main.data.AppDatabase
import com.example.taskmanager.ui.main.data.TasksRepositoryImpl
import com.example.taskmanager.ui.main.network.RestApiService
import com.example.taskmanager.ui.main.viewmodel.MainViewModel
import com.example.taskmanager.ui.main.viewmodel.MainViewModelFactory


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val api = RestApiService.createService()
        val database = AppDatabase.getInstance(requireContext().applicationContext)
        viewModel =
            ViewModelProvider(this, MainViewModelFactory(TasksRepositoryImpl(api, database))).get(
                MainViewModel::class.java
            )
        viewModel.adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                binding.recyclerView.smoothScrollToPosition(positionStart)
            }
        })
        binding.viewModel = viewModel
    }

    override fun onStart() {
        super.onStart()
        viewModel.fetchTasks()
    }
}