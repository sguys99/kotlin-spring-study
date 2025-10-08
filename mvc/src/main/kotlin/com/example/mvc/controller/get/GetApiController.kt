package com.example.mvc.controller.get
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController // Rest API 컨트롤러로 사용하겠다는 의미
@RequestMapping("/api") //?? 주소노출 위해? // http://localhost:8080/api로 노출됨
class GetApiController {

    @GetMapping("/hello") // GET http://localhost:8080/api/hello
    fun hello(): String{
        return "Hello kotlin"
    }
}

// 그런데 GetMapping 은 예전에 사용하던 방식