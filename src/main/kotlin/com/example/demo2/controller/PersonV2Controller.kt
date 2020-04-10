package com.example.demo2.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v2/person")
class PersonV2Controller {
    @GetMapping
    fun getAll() = "PersonV2Controller getAll"
}