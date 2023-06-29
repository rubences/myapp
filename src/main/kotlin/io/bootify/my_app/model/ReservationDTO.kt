package io.bootify.my_app.model

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotNull
import java.time.LocalDate
import java.time.LocalTime


class ReservationDTO {

    var id: Long? = null

    var reservationDate: LocalDate? = null

    @Schema(
        type = "string",
        example = "18:30"
    )
    var startTime: LocalTime? = null

    @Schema(
        type = "string",
        example = "18:30"
    )
    var endTime: LocalTime? = null

    @NotNull
    var user: Long? = null

}
