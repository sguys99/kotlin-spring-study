package com.example.mvc.controller.page

import com.example.mvc.model.http.UserRequest
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller // 특정한 페이지를 내릴때 사용하는 컨트롤러
class PageController {
    // 여기서 html 파일을 설정하게 된다면, resource.static 아래의 html 파일을 찾게 된다.

    @GetMapping("/main") // http://localhost:8080/main
    fun main(): String { // String 리턴으로 되어 있지만 Controller쓰고 있기 떄문에 텍스트가 리턴되는 것이 아니라 페이지가 내려진다...
        println("init main")
        return "main.html" // resource아래에 static 폴더 생성하고 그 아래에 main.html 파일을 만들자.
    }

    // 그렇다면 Controller 하에서 text body, json string을 내려야 한다면?
    // @ResponseBody 사용

    @ResponseBody
    @GetMapping("/test") // http://localhost:8080/test  -> text가 내려진다.
    fun response(): String {
        return "main.html"
    }

    // 또다른 예시
    @ResponseBody
    @GetMapping("test2") // http://localhost:8080/test2 -> json body
    fun response2(): UserRequest{
        return UserRequest().apply{
            this.name = "kmyu"
            this.age = 24
        }
    }
}
