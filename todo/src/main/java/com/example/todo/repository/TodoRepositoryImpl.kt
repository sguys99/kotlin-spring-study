package com.example.todo.repository

import com.example.todo.database.Todo
import com.example.todo.database.TodoDataBase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TodoRepositoryImpl : TodoRepository{

    @Autowired // AppConfig에서 @Bean으로 등록된 TodoDataBase 인스턴스를 자동으로 찾아서 주입
    lateinit var todoDataBase: TodoDataBase //??

    override fun save(todo: Todo): Todo? {
//        val index = ++todoDataBase.index // 마지막 인덱스 추가
//        todo.index = index
//        todo.createdAt = LocalDateTime.now()
//        todo.updatedAt = LocalDateTime.now()
//        todoDataBase.todoList.add(todo)

        // 코틀린 스티일로 수정
        // 인덱스 여부 확인
        return todo.index?.let{ index ->
            // update
            findOne(index)?.apply {
                this.title = todo.title
                this.description = todo.description
                this.schedule = todo.schedule
                this.updatedAt = LocalDateTime.now()
            }
        }?: kotlin.run {
            // insert
            todo.apply{
                this.index = ++todoDataBase.index
                this.createdAt = LocalDateTime.now()
                this.updatedAt = LocalDateTime.now()
            }.run{
                todoDataBase.todoList.add(todo)
                this
            }
        }
    }

    override fun saveAll(todoList: MutableList<Todo>): Boolean {
        return try{
            todoList.forEach{
                save(it)
            }
            true
        }catch (e: Exception){
            false
        }
    }

//    override fun update(todo: Todo): Todo {
//        TODO("Not yet implemented")
//    }

    override fun delete(index: Int): Boolean {

        return findOne(index)?.let{
            todoDataBase.todoList.remove(it)
            return true
        }?: kotlin.run {
            false
        }
    }

    override fun findOne(index: Int): Todo? {
        return todoDataBase.todoList.first { it.index == index }
    }

    override fun findAll(): MutableList<Todo> {
        return todoDataBase.todoList
    }

}