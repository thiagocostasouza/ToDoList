package com.invillia.todolist.ui

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.invillia.todolist.databinding.ActivityAddTaskBinding
import com.invillia.todolist.datasource.TaskDataSource
import com.invillia.todolist.extensions.format
import com.invillia.todolist.extensions.text
import com.invillia.todolist.model.Task
import com.invillia.todolist.ui.viewmodels.AddTaskActivityViewModel
import java.util.*

class AddTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTaskBinding
    private lateinit var viewModel: AddTaskActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (intent.hasExtra(TASK_ID)) {
            val taskId = intent.getIntExtra(TASK_ID, 0)
            TaskDataSource.findById(taskId)?.let {
                binding.inputLayoutTitle.text = it.title
                binding.inputLayoutDescription.text = it.description
                binding.inputLayoutData.text = it.date
                binding.inputLayoutHour.text = it.hour
            }
        }

        insertListeners()
        viewModel =
            ViewModelProvider.NewInstanceFactory().create(AddTaskActivityViewModel::class.java)


    }

    private fun insertListeners() {
        binding.inputLayoutData.editText?.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker().build()

            datePicker.addOnPositiveButtonClickListener {
                val timeZone = TimeZone.getDefault()
                val offset = timeZone.getOffset(Date().time) * -1
                binding.inputLayoutData.text = Date(it + offset).format()
            }
            datePicker.show(supportFragmentManager, "DATE_PICKER_TAG")
        }

        binding.inputLayoutHour.editText?.setOnClickListener {
            val timePicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .build()
            timePicker.addOnPositiveButtonClickListener {
                val minute =
                    if (timePicker.minute in 0..9) "0${timePicker.minute}" else timePicker.minute
                val hour = if (timePicker.hour in 0..9) "0${timePicker.hour}" else timePicker.hour
                binding.inputLayoutHour.text = "$hour:$minute"
            }
            timePicker.show(supportFragmentManager, null)
        }

        binding.buttonCancel.setOnClickListener {
            finish()
        }
        binding.buttonAdd.setOnClickListener {
            val task = Task(
                title = binding.inputLayoutTitle.text,
                description = binding.inputLayoutDescription.text,
                date = binding.inputLayoutData.text,
                hour = binding.inputLayoutHour.text,
                id = intent.getIntExtra(TASK_ID, 0)
            )
            TaskDataSource.insertTask(task)
            setResult(Activity.RESULT_OK)
            finish()
        }
    }

    companion object {
        const val TASK_ID = "task_id"
    }

}

