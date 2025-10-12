package com.example.todo.repository

import com.example.todo.config.AppConfig
import com.example.todo.database.Todo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDateTime


@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [TodoRepositoryImpl::class, AppConfig::class]) // 어떤 클래스를 테스트 할 것인지...
class TodoRepositoryTest{
    @Autowired
    lateinit var todoRepositoryImpl: TodoRepositoryImpl


    @BeforeEach
    fun before(){
        todoRepositoryImpl.todoDataBase.init() // 테스트함수 실행때 마다 초기화기능 추가
    }

    @Test
    fun saveTest(){
        val todo = Todo().apply{
            this.title = "테스트 일정"
            this.description = "테스트"
            this.schedule = LocalDateTime.now()
        }

        val result = todoRepositoryImpl.save(todo)

        Assertions.assertEquals(1, result?.index)
        Assertions.assertNotNull(result?.createdAt)
        Assertions.assertNotNull(result?.updatedAt)
        Assertions.assertEquals("테스트 일정", result?.title)
        Assertions.assertEquals("테스트", result?.description)
    }

    @Test
    fun saveAllTest(){
        val todoList = mutableListOf(
            Todo().apply{
                this.title = "테스트 일정"
                this.description = "테스트"
                this.schedule = LocalDateTime.now()
            },
            Todo().apply{
                this.title = "테스트 일정"
                this.description = "테스트"
                this.schedule = LocalDateTime.now()
            },
            Todo().apply{
                this.title = "테스트 일정"
                this.description = "테스트"
                this.schedule = LocalDateTime.now()
            }
        )

        val result = todoRepositoryImpl.saveAll(todoList)

        Assertions.assertEquals(true, result)
    }

    @Test
    fun findOneTest(){
        //todoRepositoryImpl.todoDataBase.init() // BeforeEach로 초기화 함수 이동

        val todoList = mutableListOf(
            Todo().apply{
                this.title = "테스트 일정1"
                this.description = "테스트"
                this.schedule = LocalDateTime.now()
            },
            Todo().apply{
                this.title = "테스트 일정2"
                this.description = "테스트"
                this.schedule = LocalDateTime.now()
            },
            Todo().apply{
                this.title = "테스트 일정3"
                this.description = "테스트"
                this.schedule = LocalDateTime.now()
            }
        )

        todoRepositoryImpl.saveAll(todoList)

        val result = todoRepositoryImpl.findOne(2)

        Assertions.assertNotNull(result)
        Assertions.assertEquals("테스트 일정2", result?.title)
    }

    @Test
    fun updateTest(){
        val todo = Todo().apply{
            this.title = "테스트 일정"
            this.description = "테스트"
            this.schedule = LocalDateTime.now()
        }
        val insetTodo = todoRepositoryImpl.save(todo)

        todoRepositoryImpl.save(todo)

        val newTodo = Todo().apply{
            this.index = insetTodo?.index
            this.title = "업데이트 일정"
            this.description = "업데이트 테스트"
            this.schedule = LocalDateTime.now()
        }

        val result = todoRepositoryImpl.save(newTodo)

        Assertions.assertNotNull(result)
        Assertions.assertEquals(insetTodo?.index, result?.index)
        Assertions.assertEquals("업데이트 일정", result?.title)
        Assertions.assertEquals("업데이트 테스트", result?.description)
    }
}