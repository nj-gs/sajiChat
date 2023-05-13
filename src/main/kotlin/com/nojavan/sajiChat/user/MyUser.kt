package com.nojavan.sajiChat.user

import jakarta.persistence.*

@Entity
@Table
data class MyUser (
    @Id @Column(name = "id") @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
    @Column(name = "username") var userName: String? = null,
    @Column(name = "password") var password: String? = null,
    @Column(name = "fullname") var fullName: String? = null,
    @Column(name = "bio") var bio: String? = null,
    @Column(name = "image-url") var imageUrl: String? = null
)