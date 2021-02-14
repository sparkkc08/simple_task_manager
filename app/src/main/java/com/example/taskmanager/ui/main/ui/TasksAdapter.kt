package com.example.taskmanager.ui.main.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.R
import com.example.taskmanager.ui.main.model.Task
import com.example.taskmanager.ui.main.viewmodel.TaskItemViewModel
import com.example.taskmanager.ui.main.utils.TaskAction

class TasksAdapter : ListAdapter<Task, TasksAdapter.ViewHolder>(
    TaskDiffCallback
), TaskAction {

    class ViewHolder(val dataBinder: ViewDataBinding) : RecyclerView.ViewHolder(dataBinder.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate<ViewDataBinding>(
                LayoutInflater.from(parent.context),
                R.layout.task_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.dataBinder.setVariable(
            BR.viewModel,
            TaskItemViewModel(
                getItem(position),
                this
            )
        )
    }

    fun addTask(task: Task) {
        submitList(listOf(task) + currentList)
    }

    object TaskDiffCallback : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }
    }

    override fun onChecked(task: Task, isChecked: Boolean) {
        val (newList, taskIndex) = currentList.toMutableList().run {
            this to indexOf(task)
        }
        if (taskIndex >= 0 && task.completed != isChecked) {
            newList[taskIndex] = task.copy(completed = isChecked)
            submitList(newList)
        }
    }
}