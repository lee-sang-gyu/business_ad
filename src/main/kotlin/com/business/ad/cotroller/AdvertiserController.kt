package com.business.ad.cotroller

import com.business.ad.dto.CreateAdvertiserDTO
import com.business.ad.dto.ReadAdvertiserDTO
import com.business.ad.dto.ReadAdvertiserJoinCamapignDTO
import com.business.ad.service.AdvertiserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

//컨트롤러: 광고주 정보
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

    //조회
    @GetMapping("/advertiser/{id}")
    fun getAdvertiserById(@PathVariable(value = "id") advertiserId: Long)
            : ResponseEntity<ReadAdvertiserDTO> {
        return ResponseEntity.ok().body(advertiserService.getAdvertiserById(advertiserId))
    }
    //조회: 캠페인정보 포함
    @GetMapping("/advertiser/{id}/campaign")
    fun getAdvertiserJoinCampaignById(@PathVariable(value = "id") advertiserId: Long)
            : ResponseEntity<ReadAdvertiserJoinCamapignDTO> {
        return ResponseEntity.ok().body(advertiserService.getAdvertiserJoinCampaignById(advertiserId))
    }

    //변경
    @PutMapping("/advertiser/{id}")
    fun updateAdvertiserById(
        @PathVariable(value = "id") advertiserId: Long,
        @RequestBody updateAdvertiser: CreateAdvertiserDTO
    ): ResponseEntity<Any> {
        return ResponseEntity.ok().body(advertiserService.updateAdvertiserById(advertiserId, updateAdvertiser))
    }

    //삭제
    @DeleteMapping("/advertiser/{id}")
    fun deleteAdvertiserById(@PathVariable(value = "id") advertiserId: Long)
            : ResponseEntity<Any> {
        return ResponseEntity.ok().body(advertiserService.deleteAdvertiserById(advertiserId))
    }
}

