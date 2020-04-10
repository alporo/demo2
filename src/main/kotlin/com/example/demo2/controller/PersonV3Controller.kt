package com.example.demo2.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v3/person")
class PersonV3Controller {
    @GetMapping
    fun getAll() = "PersonV3Controller getAll"

    @GetMapping("1")
    fun byWtf() = "PersonV3Controller byWtf hardcoded 1"
}