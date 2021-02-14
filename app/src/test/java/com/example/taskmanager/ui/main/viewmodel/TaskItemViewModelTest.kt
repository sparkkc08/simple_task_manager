package com.example.taskmanager.ui.main.viewmodel

import com.example.taskmanager.R
import com.example.taskmanager.ui.main.utils.TaskAction
import com.example.taskmanager.ui.main.model.Task
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Assert.*
import org.junit.Test


class TaskItemViewModelTest {

    @MockK(relaxed = true)
    lateinit var task: Task

    @MockK(relaxUnitFun = true)
    lateinit var actionListener: TaskAction

    private var viewModel: TaskItemViewModel

    init {
        MockKAnnotations.init(this)

        every { task.title } returns "testTitle"
        every { task.completed } returns false

        viewModel = createViewModel()
    }

    @Test
    fun titleTest() {
        assertEquals("testTitle", viewModel.title.get())
        assertEquals(R.color.black, viewModel.titleColor.get())

        every { task.completed } returns true
        viewModel = createViewModel()
        assertEquals(R.color.grey, viewModel.titleColor.get())
    }

    @Test
    fun backgroundTest() {
        viewModel = createViewModel()
        every { task.completed } returns false
        assertEquals(R.color.white, viewModel.background.get())

        every { task.completed } returns true
        viewModel = createViewModel()
        assertEquals(R.color.green, viewModel.background.get())
    }

    @Test
    fun completeTest() {
        every { task.completed } returns false
        viewModel = createViewModel()
        assertFalse(viewModel.isCompleted.get())

        every { task.completed } returns true
        viewModel = createViewModel()
        assertTrue(viewModel.isCompleted.get())
    }

    @Test
    fun onTaskClickedTest() {
        assertTrue(viewModel.isSingleLine.get())

        viewModel.onTaskClicked()
        assertFalse(viewModel.isCompleted.get())

        viewModel.onTaskClicked()
        assertTrue(viewModel.isSingleLine.get())
    }

    @Test
    fun onItemCheckedTest() {
        viewModel.onItemChecked(true)
        verify { actionListener.onChecked(task, true) }

        viewModel.onItemChecked(false)
        verify { actionListener.onChecked(task, false) }
    }

    private fun createViewModel() = TaskItemViewModel(task, actionListener)
}