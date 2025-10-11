package com.example.mvc.model.http

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import jakarta.validation.constraints.AssertTrue
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.PositiveOrZero
import jakarta.validation.constraints.Size
import jakarta.validation.constraints.Pattern
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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
data class UserRequest( // ch11 데이터 클래스에 validation 추가
    @field: NotEmpty
    @field: Size(min = 2, max = 10)
    var name: String? = null,

    @field: PositiveOrZero
    var age: Int? = null,

    @field:Email
    var email: String? = null,

    @field:NotBlank
    var address: String? = null,

    @field: Pattern(regexp = "^01[0-9]-[0-9]{3,4}-[0-9]{4}$") // ch11. 핸드폰 번호
    var phoneNumber: String? = null, // phone_number

    // ch 11. 커스텀 validation 예제를 위해 변수하자 추가하자.
    var createdAt: String? = null // yyyy-MM-dd HH:mm:ss ex) 2020-10-02 12:34:56
) { // ch11
    @AssertTrue(message = "생성일자의 패턴은 yyyy-MM-dd HH:mm:ss 여야 합니다.")
    private fun isValidCreatedAt(): Boolean {
        return try{
            LocalDateTime.parse(this.createdAt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            true
        }catch (e: Exception){
            false
        }
    }
}