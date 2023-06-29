package io.bootify.my_app.repos;

import io.bootify.my_app.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
