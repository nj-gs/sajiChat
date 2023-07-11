package com.nojavan.sajiChat.chatMessage

import jakarta.persistence.*

@Entity
@Table
data class ChatMessage (
    @Id @Column(name = "id") @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
    @Column(name = "sender_id") var senderId: String? = null,
    @Column(name = "recipient_id") var recipientId: String? = null,
    @Column(name = "content") var content: String? = null,
)
