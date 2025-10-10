package com.example.mvc.controller.response

import com.example.mvc.model.http.UserRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/response")
class ResponseApiController {
    // 1. get : 클라이언트가 잘못요청했다는 의미인 400대 만들어보기

//    @GetMapping("")// GET http://localhost:8080/api/response  or GET http://localhost:8080/api/response?age=45 or age를 추가하지 않으면 400 에러 발생
//    fun responseGet(@RequestParam(required=true) age: Int): String { // required=true : 반드시 필요하다는 의미
//        println(age)
//        return "OK"
//    }

//    // 엘비스 연산자를 추가하고 파라미터를 추가하지 않고 요청하면? -> null 이 프린트 되고 200이 수신됨
//    @GetMapping("")
//    fun responseGet(@RequestParam(required=true) age: Int?): String { // required=true : 반드시 필요하다는 의미
//        println(age)
//        return "OK"
//    }

//    // 조건을 더 초가해보자.
//    @GetMapping("")
//    fun responseGet(@RequestParam(required=true) age: Int?): ResponseEntity<String> { // required=true : 반드시 필요하다는 의미
//        // 1. age가 널이면 400 error
//        if(age == null) return ResponseEntity.status(400).body("age 값이 누락 되었습니다.")
//
//        // 2. age < 20 이면   400 error
//        if(age < 20) return ResponseEntity.status(400).body("age 값은 20보다 커야 합니다.")
//
//        println(age)
//        return ResponseEntity.ok("OK")
//    }

    // 코틀린 스타일로 개선
    // 조건을 더 초가해보자. ?? 다시 볼것
    @GetMapping("")
    fun responseGet(@RequestParam(required=true) age: Int?): ResponseEntity<String> {
        return age?.let{
            if(it < 20){
                return ResponseEntity.status(400).body("age 값은 20보다 커야 합니다.")
            }
            ResponseEntity.ok("OK")
        }?: kotlin.run {
            return ResponseEntity.status(400).body("age 값이 누락되었습니다.")
        }
    }


    // 2. post 200
    @PostMapping("") // http://localhost:8080/api/response -> 200 ok 수신   , body를 추가하면 리턴됨...
    fun postMapping(@RequestBody userRequest: UserRequest?): ResponseEntity<Any>{
        return ResponseEntity.status(200).body(userRequest)
    }

    // 3. put 201: 기존 데이터가 없어서 새로 생성했다...
    @PutMapping("")
    fun putMapping(@RequestBody userRequest: UserRequest?): ResponseEntity<Any>{
        // 1. 기존 데이터가 없어서 새로 생성했다.
        return ResponseEntity.status(HttpStatus.CREATED).body(userRequest)
    }

    // 4. delete 500 , 서버 오류
    @DeleteMapping("/{id}") // id를 하나 받는다고 가정하자.
    fun deleteMapping(@PathVariable id: Int): ResponseEntity<Any>{ // http://localhost:8080/api/response/10 -> 500 // http:localhost:8080/api/response -> 400
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
    }
}

// 지금까지는 api만 내리는 예제만 있었다.
// 페이지를 내리는 케이스를 다뤄보자.
// controller.page 로 이동하여 볼것
