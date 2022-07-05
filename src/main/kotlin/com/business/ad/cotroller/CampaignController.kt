package com.business.ad.cotroller

import com.business.ad.dto.CreateCampaignDTO
import com.business.ad.dto.ReadCampaignDTO
import com.business.ad.dto.ReadOnlyCampaignDTO
//import com.business.ad.dto.ReadCampaignJoinAdDTO
import com.business.ad.model.WebResponse
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
    @GetMapping(
        value = ["/campaign"],
        produces = ["application/json"]
    )
    fun getAllCampaign(): WebResponse<List<ReadCampaignDTO>> {
        val Response = campaignService.getCampaign()
        return WebResponse(
            code = 200,
            status = "OK",
            data = Response
        )
    }

    //캠페인 정보 Id로 조회
    @GetMapping(
        value = ["/campaign/{id}"],
        produces = ["application/json"]
    )
    fun getCampaignById(
        @PathVariable(value = "id") campaignId: Long
    ): WebResponse<ReadCampaignDTO> {
        val Response = campaignService.getCampaignById(campaignId)
        return WebResponse(
            code = 200,
            status = "OK",
            data = Response
        )
    }

    //모든 캠페인 정보 조회: 광고주, 광고내용 제외
    @GetMapping(
        value = ["/campaign_only"],
        produces = ["application/json"]
    )
    fun getAllCampaignJoinAd(): WebResponse<List<ReadOnlyCampaignDTO>> {
        val Response = campaignService.getOnlyCampaign()
        return WebResponse(
            code = 200,
            status = "OK",
            data = Response
        )
    }

    //캠페인 정보 생성
    @PostMapping(
        value = ["/campaign"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun createCampaign(@RequestBody createCampaignDTO: CreateCampaignDTO)
            : WebResponse<CreateCampaignDTO> {
        val Response = campaignService.createCampaign(createCampaignDTO)
        return WebResponse(
            code = 200,
            status = "OK",
            data = Response
        )
    }

    //캠페인 정보 Id로 변경
    @PutMapping(
        value = ["/campaign/{id}"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun updateCampaignById(
        @PathVariable(value = "id") campaignId: Long,
        @RequestBody updateCampaign: CreateCampaignDTO
    ): WebResponse<ReadCampaignDTO> {
        val Response = campaignService.updateCampaignById(campaignId, updateCampaign)
        return WebResponse(
            code = 200,
            status = "OK",
            data = Response
        )
    }

    //캠페인 정보 Id로 삭제
    @DeleteMapping(
        value = ["/campaign/{id}"],
        produces = ["application/json"]
    )
    fun deleteCampaignById(@PathVariable(value = "id") campaignId: Long)
            : WebResponse<Long> {
        campaignService.deleteCampaignById(campaignId)
        return WebResponse(
            code = 200,
            status = "OK",
            data = campaignId
        )
    }
}