package io.bootify.my_app.service

import io.bootify.my_app.domain.Reservation
import io.bootify.my_app.model.ReservationDTO
import io.bootify.my_app.repos.ReservationRepository
import io.bootify.my_app.repos.UserRepository
import io.bootify.my_app.util.NotFoundException
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service


@Service
class ReservationService(
    private val reservationRepository: ReservationRepository,
    private val userRepository: UserRepository
) {

    fun findAll(): List<ReservationDTO> {
        val reservations = reservationRepository.findAll(Sort.by("id"))
        return reservations.stream()
                .map { reservation -> mapToDTO(reservation, ReservationDTO()) }
                .toList()
    }

    fun `get`(id: Long): ReservationDTO = reservationRepository.findById(id)
            .map { reservation -> mapToDTO(reservation, ReservationDTO()) }
            .orElseThrow { NotFoundException() }

    fun create(reservationDTO: ReservationDTO): Long {
        val reservation = Reservation()
        mapToEntity(reservationDTO, reservation)
        return reservationRepository.save(reservation).id!!
    }

    fun update(id: Long, reservationDTO: ReservationDTO) {
        val reservation = reservationRepository.findById(id)
                .orElseThrow { NotFoundException() }
        mapToEntity(reservationDTO, reservation)
        reservationRepository.save(reservation)
    }

    fun delete(id: Long) {
        reservationRepository.deleteById(id)
    }

    private fun mapToDTO(reservation: Reservation, reservationDTO: ReservationDTO): ReservationDTO {
        reservationDTO.id = reservation.id
        reservationDTO.reservationDate = reservation.reservationDate
        reservationDTO.startTime = reservation.startTime
        reservationDTO.endTime = reservation.endTime
        reservationDTO.user = reservation.user?.id
        return reservationDTO
    }

    private fun mapToEntity(reservationDTO: ReservationDTO, reservation: Reservation): Reservation {
        reservation.reservationDate = reservationDTO.reservationDate
        reservation.startTime = reservationDTO.startTime
        reservation.endTime = reservationDTO.endTime
        val user = if (reservationDTO.user == null) null else
                userRepository.findById(reservationDTO.user!!)
                .orElseThrow { NotFoundException("user not found") }
        reservation.user = user
        return reservation
    }

}
