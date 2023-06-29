package io.bootify.my_app.repos

import io.bootify.my_app.domain.Reservation
import org.springframework.data.jpa.repository.JpaRepository


interface ReservationRepository : JpaRepository<Reservation, Long>
