package com.example.mvc.controller.post

import com.example.mvc.model.http.UserRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class PostApiController {

    //최근 사용방법
    @PostMapping("/post-mapping") // POST http://localhost:8080/api/post-mapping
    fun postMapping(): String{
        return "post-mapping"
    }

    // 과거 방식
    @RequestMapping(path = ["/request-mapping"], method = [RequestMethod.POST])
    fun requestMapping(): String{
        return "request-mapping"
    }

    // 그런데 포스트는 내용을 넣어서 보낼수 있다.
    // 이때 object mapper를 사용함
    // json -> object, object -> json
    @PostMapping("/post-mapping/object")
    fun postMappingObject(@RequestBody userRequest: UserRequest): UserRequest {
        // json -> object
        println(userRequest)
        // object -> json
        return userRequest
    }
}