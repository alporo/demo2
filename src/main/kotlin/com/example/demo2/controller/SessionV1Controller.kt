package com.example.demo2.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/session")
class SessionV1Controller {
    @GetMapping
    fun getAll() = "SessionV1Controller getAll"

    @GetMapping("{id}")
    fun byId(@PathVariable id: String) = "SessionV1Controller byId $id"
}