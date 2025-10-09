package com.example.mvc.controller.delete

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api")
class DeleteApiController {
    // Delete가 가질수 있는 것
    // 1. path variable
    // 2. request param

    // db가 없어서 실습은 못하고 위 1, 2를 만들어 보겠다.

    @DeleteMapping(path=["/delete-mapping"])
    fun deleteMapping(
        @RequestParam("name") _name: String,
        @RequestParam("age") _age: Int,
    ): String {
        println(_name)
        println(_age)

        return _name+ " "+_age
    }

    @DeleteMapping(path=["/delete-mapping/name/{name}/age/{age}"])
    fun deleteMappingPathV(
        @PathVariable("name") _name: String,
        @PathVariable("age") _age: Int,
    ): String {
        println(_name)
        println(_age)

        return _name+ " "+_age
    }
}