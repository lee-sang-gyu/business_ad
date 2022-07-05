package com.business.ad.service

import com.business.ad.dto.*
import com.business.ad.error.NotFoundException
import com.business.ad.model.Campaign
import com.business.ad.model.Result
import com.business.ad.repository.CampaignRepository
import com.business.ad.repository.ResultRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PathVariable
import java.util.*

//서비스: 캠페인 결과
@Component
class ResultService {
    @Autowired
    lateinit var resultRepository: ResultRepository

    @Autowired
    lateinit var campaignRepository: CampaignRepository

    //결과 전체 조회
    fun getResult(): List<ReadResultDTO> {
        val result = resultRepository.findAll()
        return result.map { it.toReadResultDTO() }
    }

    //결과 id로 조회
    fun getResultById(
        @PathVariable(value = "id") resultId: Long
    ): ReadResultDTO {
        val result = findResultByIdOrThrowNotFound(resultId)
        return result.toReadResultDTO()
    }

    //결과 조회: 캠페인 정보 포함
    fun getResultGetCampaign(): List<ReadResultGetCampaignDTO> {

        val result = resultRepository.findAll()
        val resultList = mutableListOf<ReadResultGetCampaignDTO>()
        result.forEach {

            val campaign = campaignRepository.findById(it.campaignId)

            if (campaign.isEmpty == false) {
                resultList.add(convertResultResponse(it, campaign))
            } else {
                resultList.add(convertResultResponse(it, null))
            }
        }
        return resultList
    }

    //id로 조회 조회: 캠페인 정보 포함
    fun getResultByIdGetCampaign(
        @PathVariable(value = "id") resultId: Long
    ): ReadResultGetCampaignDTO {
        val result = findResultByIdOrThrowNotFound(resultId)
        val campaign = campaignRepository.findById(result.campaignId)
        if (campaign.isEmpty == false) {
            return convertResultResponse(result, campaign)
        } else {
            return convertResultResponse(result, null)
        }
    }

    //광고 결과 생성
    fun createResult(createResultDTO: CreateResultDTO): CreateResultDTO {
        val result = resultRepository.save(createResultDTO.toEnitty())
        return result.toCreateResultDTO()
    }

    //광고 결과 변경
    fun updateResultById(
        @PathVariable(value = "id") resultId: Long,
        createResultDTO: CreateResultDTO
    ): ReadResultDTO {
        val existingResult = findResultByIdOrThrowNotFound(resultId)
        var updatedResult = existingResult.copy(
            campaignId = if (createResultDTO.campaignId == null) {
                existingResult.campaignId
            } else {
                createResultDTO.campaignId
            },
            cliks = if (createResultDTO.cliks == null) {
                existingResult.cliks
            } else {
                createResultDTO.cliks
            },
            exposure = if (createResultDTO.exposure == null) {
                existingResult.exposure
            } else {
                createResultDTO.exposure
            }
        )
        val result = resultRepository.save(updatedResult.toCreateResultDTO().toEnitty())
        return result.toReadResultDTO()
    }

    //광고 결과 삭제
    fun deleteResultById(
        @PathVariable(value = "id") resultId: Long
    ) {
        val campaign = findResultByIdOrThrowNotFound(resultId)
        resultRepository.delete(campaign)
    }

    private fun convertResultResponse(
        result: Result,
        join_campaign: Optional<Campaign>?
    ): ReadResultGetCampaignDTO {
        return ReadResultGetCampaignDTO(
            id = result.id,
            campaignid = result.campaignId,
            cliks = result.cliks,
            exposure = result.exposure,
            campaign = join_campaign
        )
    }

    private fun findResultByIdOrThrowNotFound(id: Long): Result {
        val result = resultRepository.findByIdOrNull(id)
        if (result == null) {
            throw NotFoundException()
        } else {
            return result;
        }
    }
}