package com.business.ad.cotroller

import com.business.ad.dto.CreateResultDTO
import com.business.ad.dto.ReadResultDTO
import com.business.ad.dto.ReadResultJoinCampaignDTO
import com.business.ad.model.WebResponse
import com.business.ad.service.ResultService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

//컨트롤러: 캠페인 결과
@RestController
@RequestMapping("/api")
class ResultController {
    @Autowired
    private lateinit var resultService: ResultService

    //모든 캠페인 결과 조회
    @GetMapping(
        value = ["/result"],
        produces = ["application/json"]
    )
    fun getAllResult(): WebResponse<List<ReadResultDTO>> {
        val Response = resultService.getResult()
        return WebResponse(
            code = 200,
            status = "OK",
            data = Response
        )
    }

    //모든 캠페인 결과 조회: 캠페인 정보 포함
    @GetMapping(
        value = ["/result_total"],
        produces = ["application/json"]
    )
    fun getAllResultJoinCampaign(): WebResponse<List<ReadResultJoinCampaignDTO>> {
        val Response = resultService.getResultJoinCampaign()
        return WebResponse(
            code = 200,
            status = "OK",
            data = Response
        )
    }

    //Id로 조회
    @GetMapping(
        value = ["/result/{id}"],
        produces = ["application/json"]
    )
    fun getResultById(@PathVariable(value = "id") resultId: Long)
            : WebResponse<ReadResultDTO> {
        val Response = resultService.getResultById(resultId)
        return WebResponse(
            code = 200,
            status = "OK",
            data = Response
        )
    }

    //캠페인 결과 Id로 조회: 캠페인 정보 포함
    @GetMapping(
        value = ["/result_total/{id}"],
        produces = ["application/json"]
    )
    fun getResultJoinCampaignById(
        @PathVariable(value = "id") resultId: Long
    ): WebResponse<ReadResultJoinCampaignDTO> {
        val Response = resultService.getResultJoinCampaignById(resultId)
        return WebResponse(
            code = 200,
            status = "OK",
            data = Response
        )
    }

    //캠페인 결과 생성
    @PostMapping(
        value = ["/result"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun createResult(@RequestBody createResultDTO: CreateResultDTO)
            : WebResponse<CreateResultDTO> {
        val Response = resultService.createResult(createResultDTO)
        return WebResponse(
            code = 200,
            status = "OK",
            data = Response
        )
    }

    //캠페인 결과 Id로 변경
    @PutMapping(
        value = ["/result/{id}"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun updateResultById(
        @PathVariable(value = "id") resultId: Long,
        @RequestBody updateResult: CreateResultDTO
    ): WebResponse<ReadResultDTO> {
        val Response = resultService.updateResultById(resultId, updateResult)
        return WebResponse(
            code = 200,
            status = "OK",
            data = Response
        )
    }

    //캠페인 결과 Id로 삭제
    @DeleteMapping(
        value = ["/result/{id}"],
        produces = ["application/json"]
    )
    fun deleteResultById(@PathVariable(value = "id") resultId: Long)
            : WebResponse<Long> {
        resultService.deleteResultById(resultId)
        return WebResponse(
            code = 200,
            status = "OK",
            data = resultId
        )
    }
}