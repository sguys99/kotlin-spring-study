package com.example.todo.database

data class TodoDataBase(
    var index: Int = 0,
    var todoList: MutableList<Todo> =mutableListOf() // 컬럼이 들어감
    ){
    fun init(){
        this.index = 0
        this.todoList = mutableListOf()
        println("[DEBUG] TodoDataBase init")
    }
}