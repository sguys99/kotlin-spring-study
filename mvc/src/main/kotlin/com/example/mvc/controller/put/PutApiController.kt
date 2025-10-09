package com.example.mvc.controller.put

import com.example.mvc.model.http.Result
import com.example.mvc.model.http.UserRequest
import com.example.mvc.model.http.UserResponse
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class PutApiController {

    @PutMapping("/put-mapping")
    fun putMapping(): String {
        return "put-mapping"
    }

    @RequestMapping(method = [RequestMethod.PUT], path = ["/request-mapping"])
    fun requestMapping(): String{
        return "request-mapping - put method"
    }

//    @PutMapping(path = ["/put-mapping/object"])
//    fun putMappingObject(@RequestBody userRequest: UserRequest): UserRequest {
//        return userRequest
//    }

    // 이제 UserResponse가 가도록 기능을 만들어보자.
    @PutMapping(path=["/put-mapping/object"])
    fun putMappingObject(@RequestBody userRequest: UserRequest): UserResponse {
        // 0. Response
        return UserResponse().apply{
            // 1. result
            this.result  = Result().apply {
                this.resultCode = "OK"
                this.resultMessage = "성공"
            }
        }.apply{

            this.description = "~~~~~~~~"
        }.apply{
            // 3. user mutable list
            val userList = mutableListOf<UserRequest>()

            userList.add(userRequest)

            userList.add(UserRequest().apply{
                this.name = "a"
                this.age = 10
                this.email = "a@gmail.com"
                this.address = "a"
                this.phoneNumber = "010-1000-1000"
            })

            userList.add(UserRequest().apply{
                this.name = "b"
                this.age = 20
                this.email = "a@gmail.com"
                this.address = "a"
                this.phoneNumber = "010-1000-1100"
            })
            this.userRequest = userList
        }
    }

}