package com.business.ad.cotroller

import com.business.ad.model.Advertiser
import com.business.ad.dto.CreateAdvertiserDTO
import com.business.ad.dto.ReadAdvertiserDTO
import com.business.ad.service.AdvertiserService
import com.business.ad.repository.AdvertiserRepository
import org.springframework.beans.factory.annotation.Autowired


import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class AdvertiserController {
    @Autowired
    private lateinit var advertiserService: AdvertiserService

    @GetMapping("/advertiser")
    fun getAllAdvertiser(): ResponseEntity<Any> {
        return ResponseEntity.ok().body(advertiserService.getAdvertisers())
    }

    @PostMapping("/advertiser")
    fun createAdvertiser(@RequestBody createAdvertiserDTO: CreateAdvertiserDTO)
            : ResponseEntity<Any> {
        advertiserService.createAdvertiser(createAdvertiserDTO)
        return ResponseEntity.ok().body(true)
    }

    @GetMapping("/advertiser/{id}")
    fun getAdvertiserById(@PathVariable(value = "id") advertiserId: Long)
            : ResponseEntity<ReadAdvertiserDTO> {
        return ResponseEntity.ok().body(advertiserService.getAdvertiserById(advertiserId))
    }

    @PutMapping("/advertiser/{id}")
    fun updateAdvertiserById(
        @PathVariable(value = "id") advertiserId: Long,
        @RequestBody updateAdvertiser: CreateAdvertiserDTO
    ): ResponseEntity<Any> {
        return ResponseEntity.ok().body(advertiserService.updateAdvertiserById(advertiserId, updateAdvertiser))
    }

    @DeleteMapping("/advertiser/{id}")
    fun deleteAdvertiserById(@PathVariable(value = "id") advertiserId: Long)
            : ResponseEntity<Any> {
        return ResponseEntity.ok().body(advertiserService.deleteAdvertiserById(advertiserId))
    }
}

