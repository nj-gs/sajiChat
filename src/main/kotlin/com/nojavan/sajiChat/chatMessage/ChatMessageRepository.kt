package com.nojavan.sajiChat.chatMessage

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ChatMessageRepository: JpaRepository<ChatMessage, Long>