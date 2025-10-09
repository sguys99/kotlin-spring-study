package com.example.mvc.controller.get
import com.example.mvc.model.http.UserRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam

@RestController // Rest API 컨트롤러로 사용하겠다는 의미
@RequestMapping("/api") //?? 주소노출 위해? // http://localhost:8080/api로 노출됨
class GetApiController {

    @GetMapping("/hello") // GET http://localhost:8080/api/hello
    //@GetMapping(path = ["/hello", "/abcd"]) // path 설정으로 경로를 여러개 지정해줄수도 있다.
    fun hello(): String{
        return "Hello kotlin"
    }
    // 그런데 GetMapping은 현재 사용하는 방식..
    // 과거 사용하는 방식을 알아보자.
    @RequestMapping(method = [RequestMethod.GET], path = ["/request-mapping"])
    fun requestMapping(): String{
        return "request-mapping"
    }

    // path variable를 노출하는 방법
    @GetMapping("/get-mapping/path-variable/{name}/{age}") // GET http://localhost:8080/api/get-mapping/path-variable/kmyu
    fun pathVariable(@PathVariable name: String, @PathVariable age:Int): String{
        println("${name} , ${age}")
        return name + " , " + age
    }

    // 또다른 예시: 함수안에 name이라는 변수를 사용할 경우도 있을 것이다.
    // 이때는 @PathVariable에 value라는 속성을 사용하면된다.
    @GetMapping("/get-mapping/path-variable2/{name}/{age}") // GET http://localhost:8080/api/get-mapping/path-variable/kmyu
    fun pathVariable2(@PathVariable("name") _name: String, @PathVariable age:Int): String{
        val name = "kotlin"
        println("${_name} , ${age}")
        return _name + " , " + age
    }

    // 쿼리 파라미터를 받는 방법
    // http://localhost:8080/api/page?name=kmyu&age=24&... 계속 ..
    @GetMapping("/get-mapping/query-param")// 주소에는 노출하지 않는다.
    fun queryParam(
        @RequestParam(value = "name") name: String,
        @RequestParam(value="age") age: Int
    ): String{
        println("${name} , ${age}")
        return name + " , " + age
    }

    // 파라미터가 매우 많아 지는 경우... name, age, address, email
    // 객체로 받는 방법이 있다.
    // 패키지에 클래스를 정의해서 사용하자...
    // model.http 패키지를 만들자 그리고 여기에 UserRequest 클래스를 만든다.
    // UserRequest 부분 볼것
    @GetMapping("/get-mapping/query-param/object")
    fun queryParamObject(userRequest: UserRequest): UserRequest{
        println(userRequest)
        return userRequest
    }

    // get으로 query 파라미터를 받는 또다른 방법
    @GetMapping("/get-mapping/query-param/map")
    fun queryParamMap(@RequestParam map: Map<String, Any>): Map<String, Any> {
        println(map)
        return map
    }

}
