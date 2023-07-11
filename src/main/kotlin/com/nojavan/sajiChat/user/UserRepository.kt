package com.nojavan.sajiChat.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<MyUser, Long>{
    fun findByUserName(username:String):MyUser?

    fun existsByUserName(username: String):Boolean
}