package com.nojavan.sajiChat

import com.nojavan.sajiChat.user.MyUser
import org.springframework.security.core.Authentication


fun Authentication.toUser(): MyUser {
    return principal as MyUser
}
