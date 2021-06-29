package com.invillia.todolist.datasource

import com.invillia.todolist.model.TaskEntity

object TaskDataSource {
    private val list = arrayListOf<TaskEntity>()

    fun getList() = list.toList()

    fun insertTask(task: TaskEntity) {
        if (task.id == 0){
            list.add(task.copy(id = list.size + 1))
        }else{
            list.remove(task)
            list.add(task)
        }
    }
    fun findById(taskId: Int) = list.find { it.id == taskId }

    fun deleteTask(task: TaskEntity) {
        list.remove(task)
    }

}