package com.nojavan.sajiChat.controller

import com.nojavan.sajiChat.toUser
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/api")
class ItemController {
    @GetMapping("/test")
    @ResponseBody
    fun someRequest(authentication: Authentication): String {
        val authUser = authentication.toUser()

        return "Hello ${authUser.userName}"
    }

    @GetMapping("/test2")
    @ResponseBody
    fun someRequest(): String {
        return "Hello"
    }
}