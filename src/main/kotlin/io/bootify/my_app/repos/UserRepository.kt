package io.bootify.my_app.repos

import io.bootify.my_app.domain.User
import org.springframework.data.jpa.repository.JpaRepository


interface UserRepository : JpaRepository<User, Long>
