package com.business.ad

import mu.KotlinLogging
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

private val logger = KotlinLogging.logger {}

@SpringBootApplication
class AdApplication

fun main(args: Array<String>) {
    runApplication<AdApplication>(*args)
}
