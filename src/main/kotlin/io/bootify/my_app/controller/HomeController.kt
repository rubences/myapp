package io.bootify.my_app.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping


@Controller
class HomeController {

    @GetMapping("/")
    fun index(): String = "home/index"

}
