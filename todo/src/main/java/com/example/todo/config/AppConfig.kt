package com.example.todo.config

import com.example.todo.database.TodoDataBase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open // 어플리케이션이 구동될때 참조할 것들...?
class AppConfig {

    @Bean(initMethod = "init")
    open // init 메서드를 참조??
    fun todoDataBase(): TodoDataBase {
        return TodoDataBase()
    }
}