package com.business.ad.cotroller

import com.business.ad.dto.CreateAdvertiserDTO
import com.business.ad.dto.ReadAdvertiserDTO
import com.business.ad.dto.ReadAdvertiserJoinCamapignDTO
import com.business.ad.model.WebResponse
import com.business.ad.service.AdvertiserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

//컨트롤러: 광고주 정보
@RestController
@RequestMapping("/api")
class AdvertiserController {
    @Autowired
    private lateinit var advertiserService: AdvertiserService

    @GetMapping(
        value = ["/advertiser"],
        produces = ["application/json"]
    )
    fun getAllAdvertiser(): WebResponse<List<ReadAdvertiserDTO>> {
        val Response = advertiserService.getAdvertisers()
        return WebResponse(
            code = 200,
            status = "OK",
            data = Response
        )
    }

    //광고주 생성
    @PostMapping(
        value = ["/advertiser"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun createAdvertiser(@RequestBody createAdvertiserDTO: CreateAdvertiserDTO)
            : WebResponse<String> {
        val Response = advertiserService.createAdvertiser(createAdvertiserDTO)
        return WebResponse(
            code = 200,
            status = "OK",
            data = Response.name
        )
    }

    //id로 조회
    @GetMapping(
        value = ["/advertiser/{id}"],
        produces = ["application/json"]
    )
    fun getAdvertiserById(@PathVariable(value = "id") advertiserId: Long)
            : WebResponse<ReadAdvertiserDTO> {
        val Response = advertiserService.getAdvertiserById(advertiserId)
        return WebResponse(
            code = 200,
            status = "OK",
            data = Response
        )
    }

    //조회: 캠페인정보 포함
    @GetMapping(
        value = ["/advertiser/{id}/campaign"],
        produces = ["application/json"]
    )
    fun getAdvertiserJoinCampaignById(@PathVariable(value = "id") advertiserId: Long)
            : WebResponse<ReadAdvertiserJoinCamapignDTO> {
        val Response = advertiserService.getAdvertiserJoinCampaignById(advertiserId)
        return WebResponse(
            code = 200,
            status = "OK",
            data = Response
        )
    }

    //광고주 변경
    @PutMapping(
        value = ["/advertiser/{id}"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun updateAdvertiserById(
        @PathVariable(value = "id") advertiserId: Long,
        @RequestBody updateAdvertiser: CreateAdvertiserDTO
    ): WebResponse<CreateAdvertiserDTO> {
        val Response = advertiserService.updateAdvertiserById(advertiserId, updateAdvertiser)
        return WebResponse(
            code = 200,
            status = "OK",
            data = Response
        )
    }

    //삭제
    @DeleteMapping(
        value = ["/advertiser/{id}"],
        produces = ["application/json"]
    )
    fun deleteAdvertiserById(@PathVariable(value = "id") advertiserId: Long)
            : WebResponse<Long> {
        advertiserService.deleteAdvertiserById(advertiserId)
        return WebResponse(
            code = 200,
            status = "OK",
            data = advertiserId
        )
    }
}

