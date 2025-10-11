package com.example.mvc.controller.exception

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/exception")
class ExceptionApiController {

    @GetMapping("/hello")
    fun hello(){
        if(true){
            throw RuntimeException("강제 exception 발생")
        }
    }
} //http://localhost:8080/api/exception/hello
// 500 exception 발생함
// 디펄트로 다음 값이 발생
//{
//    "timestamp": "2025-10-11T10:21:42.759+00:00",
//    "status": 500,
//    "error": "Internal Server Error",
//    "path": "/api/exception/hello"
//}