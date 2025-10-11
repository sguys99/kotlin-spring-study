package com.example.mvc.controller.delete
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api")
@Validated // ch11. 이 어노테이션을 추가해줘야 validation이 동작한다.
class DeleteApiController {
    // Delete가 가질수 있는 것
    // 1. path variable
    // 2. request param

    // db가 없어서 실습은 못하고 위 1, 2를 만들어 보겠다.

    @DeleteMapping(path=["/delete-mapping"]) // ch11. 요청 validation을 하는 것을 추가해보자.
    fun deleteMapping(
        @RequestParam("name") _name: String,

        @NotNull(message = "age 값이 누락되었습니다.") // ch11. null validation
        @Min(20, message = "20보다 커야합니다.") // ch11. min validation
        // CH 11. 대신 BEAN이 아니기 때문에 Validated 어노테이션 필요
        @RequestParam("age") _age: Int,
    ): String {
        println(_name)
        println(_age)

        return _name+ " "+_age
    } // http://localhost:8080/api/delete-mapping?name=kmyu&age=10을 입력하면 500 서버에러 발생
        // deleteMapping._age: 20보다 커야합니다.

    @DeleteMapping(path=["/delete-mapping/name/{name}/age/{age}"])
    fun deleteMappingPathV(
        @NotNull
        @Size(min = 2, max = 10) // ch11. size validation, 문자열 길이
        @PathVariable("name") _name: String,

        // ch11. 같은 방식으로 path variable에도 추가해보자.
        @NotNull(message = "age 값이 누락되었습니다.") // ch11. null validation
        @Min(20, message = "20보다 커야합니다.") // ch11. min validation
        @PathVariable("age") _age: Int,
    ): String {
        println(_name)
        println(_age)

        return _name+ " "+_age
    }
}