package com.business.ad.service

import com.business.ad.dto.CreateCampaignDTO
import com.business.ad.dto.CreateResultDTO
import com.business.ad.dto.ReadCampaignDTO
import com.business.ad.dto.ReadResultDTO
import com.business.ad.repository.ResultRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PathVariable

//서비스: 캠페인 결과
@Component
class ResultService {
    @Autowired
    lateinit var resultRepository: ResultRepository

    //결과 전체 조회
    fun getResult(): List<ReadResultDTO> {
        val result = resultRepository.findAll()
        return result.map { it.toReadResultDTO() }
    }

    //광고 결과 생성
    @Transactional
    fun createResult(createResultDTO: CreateResultDTO): CreateResultDTO {
        val result = resultRepository.save(createResultDTO.toEnitty())
        return result.toCreateResultDTO()
    }

    //광고 결과 변경
    fun updateResultById(
        @PathVariable(value = "id") resultId: Long,
        createResultDTO: CreateResultDTO
    ): ReadResultDTO {
        val existingResult = resultRepository.findById(resultId)

        return if (existingResult.isEmpty) {
            ReadResultDTO(resultId, 0, 0, 0)
        } else {
            var updatedResult = existingResult.get().copy(
                campaignid = createResultDTO.campaignid,
                cliks = createResultDTO.cliks,
                exposure = createResultDTO.exposure
            )
            val result = resultRepository.save(updatedResult.toCreateResultDTO().toEnitty())
            result.toReadResultDTO()
        }
    }

    //광고 결과 삭제
    fun deleteResultById(
        @PathVariable(value = "id") resultId: Long
    ): Boolean {
        val campaign = resultRepository.findById(resultId)
        return if (campaign.isEmpty) {
            return false
        } else {
            campaign.map { deleteCampaign -> resultRepository.delete(deleteCampaign) }
            val checkResult = resultRepository.findById(resultId)
            return checkResult.isEmpty
        }
    }
}