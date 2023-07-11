package com.nojavan.sajiChat.user

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserService (
    private val userRepo:UserRepository
        ){
    fun findById(userId: Long): MyUser? {
        return userRepo.findByIdOrNull(userId)
    }

    fun findByUserName(username: String): MyUser? {
        return userRepo.findByUserName(username)
    }

    fun existsByUserName(username: String): Boolean {
        return userRepo.existsByUserName(username)
    }

    fun save(user: MyUser): MyUser {
        return userRepo.save(user)
    }
}