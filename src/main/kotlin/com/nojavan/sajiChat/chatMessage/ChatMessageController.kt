package com.nojavan.sajiChat.chatMessage

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@Controller
@RequestMapping("/api/chat")
class ChatMessageController {

    @Autowired
    private lateinit var chatMessageRepository: ChatMessageRepository

    @GetMapping("/messages")
    @ResponseBody
    fun showChatMessages():List<ChatMessage> = chatMessageRepository.findAll()

    @GetMapping("/messages/{id}")
    @ResponseBody
    fun getChatMessage(@PathVariable id:Long): ChatMessage {
        val chatMessage = chatMessageRepository.findById(id)
        return chatMessage.get()
    }

    @PostMapping("/messages")
    @ResponseBody
    fun addChatMessage(@RequestBody chatMessage: ChatMessage): ResponseEntity<Any> {
        val savedChatMessage = chatMessageRepository.save(chatMessage)
        val newChatMessage =
            ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedChatMessage.id).toUri()
        return ResponseEntity.created(newChatMessage).build()
    }

    @DeleteMapping("/messages/{id}")
    @ResponseBody
    fun deleteChatMessage(@PathVariable id:Long) = chatMessageRepository.deleteById(id)

    @PutMapping("/messages/{id}")
    @ResponseBody
    fun updateChatMessage(@RequestBody chatMessage: ChatMessage, @PathVariable id: Long): ResponseEntity<Any> {
        val oldChatMessage = chatMessageRepository.findById(id)
        if (!oldChatMessage.isPresent) return ResponseEntity.notFound().build()
        chatMessage.id = id
        chatMessageRepository.save(chatMessage)
        return ResponseEntity.noContent().build()
    }
}