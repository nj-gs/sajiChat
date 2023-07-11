package com.nojavan.sajiChat.controller

import com.nojavan.sajiChat.dto.ApiException
import com.nojavan.sajiChat.dto.LoginDto
import com.nojavan.sajiChat.dto.LoginResponseDto
import com.nojavan.sajiChat.dto.RegisterDto
import com.nojavan.sajiChat.user.MyUser
import com.nojavan.sajiChat.user.UserService
import com.nojavan.sajiChat.utils.jwt.TokenService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class AuthController(
    private val hashService: HashService,
    private val tokenService: TokenService,
    private val userService: UserService,
) {
    @PostMapping("/login")
    fun login(@RequestBody payload: LoginDto): LoginResponseDto {
        val user = userService.findByUserName(payload.username ) ?: throw ApiException(400, "Login failed")

        if (!hashService.checkBcrypt(payload.password, user.password!!)) {
            throw ApiException(400, "Login failed")
        }


        return LoginResponseDto(
            token = tokenService.createToken(user),
        )
    }

    @PostMapping("/register")
    fun register(@RequestBody payload: RegisterDto): LoginResponseDto {
        if (userService.existsByUserName(payload.username)) {
            throw ApiException(400, "Name already exists")
        }

        val user = MyUser(
            userName = payload.username,
            password = hashService.hashBcrypt(payload.password),
        )

        val savedUser = userService.save(user)

        return LoginResponseDto(
            token = tokenService.createToken(savedUser),
        )
    }
}