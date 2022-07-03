package com.business.ad.service

import com.business.ad.dto.*
import com.business.ad.model.Adcontent
import com.business.ad.model.Campaign
import com.business.ad.model.Result
import com.business.ad.repository.AdcontentRepository
import com.business.ad.repository.CampaignRepository
import com.business.ad.repository.ResultRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PathVariable
import java.util.regex.Pattern

//서비스: 캠페인 결과
@Component
class ResultService {
    @Autowired
    lateinit var resultRepository: ResultRepository
    @Autowired
    lateinit var campaignRepository: CampaignRepository
    @Autowired
    lateinit var adcontentRepository: AdcontentRepository

    //결과 전체 조회
    fun getResult(): List<ReadResultDTO> {
        val result = resultRepository.findAll()
        return result.map { it.toReadResultDTO() }
    }

    //결과 id로 조회
    fun getResultById(
        @PathVariable(value = "id") resultId: Long
    ): ReadResultDTO {
        val result = resultRepository.findById(resultId)
        return if (result.isEmpty) {
            ReadResultDTO(resultId, 0, 0, 0)
        } else {
            result.get().toReadResultDTO()
        }
    }

    //결과 조회: 캠페인 정보 포함
    fun getResultJoinCampaign(): List<ReadResultJoinCampaignDTO> {

        val result = resultRepository.findAll()
        val resultList = mutableListOf<ReadResultJoinCampaignDTO>()
        result.forEach {
            val campaign = campaignRepository.findById(it.campaignid)
            //광고 내용
            val arr_adc_id = Pattern.compile("/").split(campaign.get().adcontentid)
            val arr_long_adc_id = mutableListOf<Long>()
            if (arr_adc_id.get(0) != "") {
                arr_adc_id.forEach {
                    arr_long_adc_id.add(it.replace("$", "").toLong())
                }
            }
            val adcontent = adcontentRepository.findByIdIn(arr_long_adc_id)
            val adcontent2 = convertCampaignResponse(campaign.get(), adcontent)
            resultList.add( convertResultResponse(it, adcontent2))
        }
        return resultList
    }

    //id로 조회 조회: 광고주, 광고내용 포함
    fun getResultJoinCampaignById(
        @PathVariable(value = "id") resultId: Long
    ): ReadResultJoinCampaignDTO {
        val result = resultRepository.findById(resultId)
        //캠페인 정보
        val campaign = campaignRepository.findById(result.get().campaignid)
        //광고 내용
        val arr_adc_id = Pattern.compile("/").split(campaign.get().adcontentid)
        val arr_long_adc_id = mutableListOf<Long>()
        if (arr_adc_id.get(0) != "") {
            arr_adc_id.forEach {
                arr_long_adc_id.add(it.replace("$", "").toLong())
            }
        }
        val adcontent = adcontentRepository.findByIdIn(arr_long_adc_id)
        val adcontent2 = convertCampaignResponse(campaign.get(), adcontent)
        return convertResultResponse(result.get(), adcontent2)
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

    private fun convertResultResponse(
        result: Result,
        join_campaign: ReadCampaignJoinAdcDTO
    ): ReadResultJoinCampaignDTO {
        return ReadResultJoinCampaignDTO(
            id = result.id,
            campaignid = result.campaignid,
            cliks = result.cliks,
            exposure = result.exposure,
            campaign = join_campaign
        )
    }

    private fun convertCampaignResponse(
        campaign: Campaign,
        join_adcontent: List<Adcontent>
    ): ReadCampaignJoinAdcDTO {
        return ReadCampaignJoinAdcDTO(
            id = campaign.id,
            name = campaign.name,
            start_date = campaign.start_date,
            end_date = campaign.end_date,
            subject_list = campaign.subject_list,
            advertiserid = campaign.advertiserid,
            adcontentid = campaign.adcontentid,
            adcontent = join_adcontent
        )
    }
}