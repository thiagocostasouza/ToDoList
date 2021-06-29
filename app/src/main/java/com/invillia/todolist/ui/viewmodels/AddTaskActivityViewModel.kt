package com.invillia.todolist.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.invillia.todolist.datasource.TaskDataSource
import com.invillia.todolist.model.TaskEntity

class AddTaskActivityViewModel(
    private val dataSource: TaskDataSource )
    : ViewModel() {





    private val _listTask = MutableLiveData<List<TaskEntity>>()
    val listTask: LiveData<List<TaskEntity>>
        get() = _listTask


    }




