package com.nojavan.sajiChat.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@Controller
class UserController {

    @Autowired
    private lateinit var userRepository:UserRepository

    @GetMapping("/users")
    @ResponseBody
    fun showUsers():List<MyUser> = userRepository.findAll()

    @GetMapping("/users/{id}")
    @ResponseBody
    fun getUser(@PathVariable id:Long):MyUser{
        val user = userRepository.findById(id)
        return user.get()
    }

    @DeleteMapping("/users/{id}")
    @ResponseBody
    fun deleteUser(@PathVariable id:Long) = userRepository.deleteById(id)

    @PostMapping("/users")
    @ResponseBody
    fun createUser(@RequestBody myUser:MyUser): ResponseEntity<Any>{
        val savedUser = userRepository.save(myUser)
        val newUser =
            ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.id).toUri()
        return ResponseEntity.created(newUser).build()
    }

    @PutMapping("/users/{id}")
    @ResponseBody
    fun updateUser(@RequestBody myUser: MyUser, @PathVariable id: Long): ResponseEntity<Any> {
        val userOptional = userRepository.findById(id)
        if (!userOptional.isPresent) return ResponseEntity.notFound().build()
        myUser.id = id
        userRepository.save(myUser)
        return ResponseEntity.noContent().build()
    }


}