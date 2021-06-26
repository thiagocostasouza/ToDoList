package com.invillia.todolist

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.invillia.todolist.databinding.ActivityMainBinding
import com.invillia.todolist.datasource.TaskDataSource
import com.invillia.todolist.ui.AddTaskActivity
import com.invillia.todolist.ui.TaskListAdapter


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy { TaskListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.rvTasks.adapter = adapter
        updateList()

        insertListeners()
    }

    private fun insertListeners() {
        binding.fabAdd.setOnClickListener {
            val intent = Intent(this, AddTaskActivity::class.java)
            startActivityForResult(intent, CREATE_NEW_TASK)
        }

        adapter.listenerEdit = {
            val intent = Intent(this, AddTaskActivity::class.java)
            intent.putExtra(AddTaskActivity.TASK_ID, it.id)
            startActivityForResult(intent, CREATE_NEW_TASK)
        }

        adapter.listenerDelete = {
            TaskDataSource.deleteTask(it)
            updateList()

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CREATE_NEW_TASK && resultCode == Activity.RESULT_OK) updateList()
    }

    private fun updateList() {
        val list = TaskDataSource.getList()
        binding.includeEmpty.emptyState.visibility = if (list.isEmpty()) {
            View.VISIBLE
        } else
            View.GONE

        adapter.submitList(list)
    }

    companion object {
        private const val CREATE_NEW_TASK = 1000
    }

}