package com.example.mvc.model.http

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

//data class UserRequest(
//    val name: String? = null,
//    val age: Int? = null,
//    val email: String? = null,
//    var address: String? = null,
//    // var phoneNumber: String? = null // 코틀린에서는 카멜케이스 사용, rest api는 snake case 사용 phone_number
//    // 매칭이 안될수 있다...
//    // 대안 object 매퍼 사용
//    @JsonProperty("phone_number")
//    var phoneNumber: String? = null // phone_number
//)

// 그런데 클래스에 네이밍을 지정해주는 방법도 있다.
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class UserRequest(
    val name: String? = null,
    val age: Int? = null,
    val email: String? = null,
    var address: String? = null,
    var phoneNumber: String? = null // phone_number
)
// 위와 동일하다.