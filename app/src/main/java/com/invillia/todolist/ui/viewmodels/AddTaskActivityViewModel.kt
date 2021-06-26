package com.invillia.todolist.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.invillia.todolist.datasource.TaskDataSource
import com.invillia.todolist.model.Task

class AddTaskActivityViewModel() : ViewModel() {





    private val _listTask = MutableLiveData<List<Task>>()
    val listTask: LiveData<List<Task>>
        get() = _listTask


    }




