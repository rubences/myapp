package io.bootify.my_app.rest

import io.bootify.my_app.model.ReservationDTO
import io.bootify.my_app.service.ReservationService
import io.swagger.v3.oas.annotations.responses.ApiResponse
import jakarta.validation.Valid
import java.lang.Void
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(
    value = ["/api/reservations"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class ReservationResource(
    private val reservationService: ReservationService
) {

    @GetMapping
    fun getAllReservations(): ResponseEntity<List<ReservationDTO>> =
            ResponseEntity.ok(reservationService.findAll())

    @GetMapping("/{id}")
    fun getReservation(@PathVariable(name = "id") id: Long): ResponseEntity<ReservationDTO> =
            ResponseEntity.ok(reservationService.get(id))

    @PostMapping
    @ApiResponse(responseCode = "201")
    fun createReservation(@RequestBody @Valid reservationDTO: ReservationDTO):
            ResponseEntity<Long> {
        val createdId = reservationService.create(reservationDTO)
        return ResponseEntity(createdId, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateReservation(@PathVariable(name = "id") id: Long, @RequestBody @Valid
            reservationDTO: ReservationDTO): ResponseEntity<Long> {
        reservationService.update(id, reservationDTO)
        return ResponseEntity.ok(id)
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    fun deleteReservation(@PathVariable(name = "id") id: Long): ResponseEntity<Void> {
        reservationService.delete(id)
        return ResponseEntity.noContent().build()
    }

}
