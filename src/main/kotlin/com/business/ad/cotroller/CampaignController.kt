package com.business.ad.cotroller

import com.business.ad.dto.CreateCampaignDTO
import com.business.ad.service.CampaignService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

//컨트롤러: 캠페인 정보
@RestController
@RequestMapping("/api")
class CampaignController {
    @Autowired
    private lateinit var campaignService: CampaignService

    //모든 캠페인 정보 조회
    @GetMapping("/campaign")
    fun getAllCampaign(): ResponseEntity<Any> {
        return ResponseEntity.ok().body(campaignService.getCampaign())
    }

    //캠페인 정보 Id로 조회
    @GetMapping("/campaign/{id}")
    fun getCampaignById(
        @PathVariable(value = "id") campaignId: Long
    ): ResponseEntity<Any> {
        return ResponseEntity.ok().body(campaignService.getCampaignById(campaignId))
    }

    //모든 캠페인 정보 조회: 광고주, 광고내용 포함
    @GetMapping("/campaign_total")
    fun getAllCampaignJoinAd(): ResponseEntity<Any> {
        return ResponseEntity.ok().body(campaignService.getCampaignJoinAd())
    }

    //캠페인 정보 Id로 조회: 광고주, 광고내용 포함
    @GetMapping("/campaign_total/{id}")
    fun getCampaignJoinAdById(
        @PathVariable(value = "id") campaignId: Long
    ): ResponseEntity<Any> {
        return ResponseEntity.ok().body(campaignService.getCampaignJoinAdById(campaignId))
    }

    //캠페인 정보 생성
    @PostMapping("/campaign")
    fun createCampaign(@RequestBody createCampaignDTO: CreateCampaignDTO)
            : ResponseEntity<Any> {
        campaignService.createCampaign(createCampaignDTO)
        return ResponseEntity.ok().body(true)
    }

    //캠페인 정보 Id로 변경
    @PutMapping("/campaign/{id}")
    fun updateCampaignById(
        @PathVariable(value = "id") campaignId: Long,
        @RequestBody updateCampaign: CreateCampaignDTO
    ): ResponseEntity<Any> {
        return ResponseEntity.ok().body(campaignService.updateCampaignById(campaignId, updateCampaign))
    }

    //캠페인 정보 Id로 삭제
    @DeleteMapping("/campaign/{id}")
    fun deleteCampaignById(@PathVariable(value = "id") campaignId: Long)
            : ResponseEntity<Any> {
        return ResponseEntity.ok().body(campaignService.deleteCampaignById(campaignId))
    }
}