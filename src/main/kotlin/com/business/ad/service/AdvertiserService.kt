package com.business.ad.service

import com.business.ad.dto.CreateAdvertiserDTO
import com.business.ad.dto.ReadAdvertiserDTO
import com.business.ad.dto.ReadAdvertiserJoinCamapignDTO
import com.business.ad.dto.ReadCampaignJoinAdDTO
import com.business.ad.repository.AdvertiserRepository
import com.business.ad.repository.CampaignRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PathVariable

//서비스: 광고주 정보
@Component
class AdvertiserService {

    @Autowired
    lateinit var advertiserRepository: AdvertiserRepository
    @Autowired
    lateinit var campaignRepository: CampaignRepository

    fun getAdvertisers(): List<ReadAdvertiserDTO> {
        val advertiser = advertiserRepository.findAll()
        return advertiser.map { it.toReadAdvertiserDTO() }
    }

    //광고주 생성
    @Transactional
    fun createAdvertiser(createAdvertiserDTO: CreateAdvertiserDTO): CreateAdvertiserDTO {
        val advertiser = advertiserRepository.save(createAdvertiserDTO.toEnitty())
        return advertiser.toCreateAdvertiserDTO()
    }

    //광고주 id로 조회
    fun getAdvertiserById(@PathVariable(value = "id") advertiserId: Long): ReadAdvertiserDTO {
        val advertiser = advertiserRepository.findById(advertiserId)

        return if (advertiser.isEmpty) {
            ReadAdvertiserDTO(advertiserId, "")
        } else {
            advertiser.get().toReadAdvertiserDTO()
        }
    }

    //광고주 조회: 캠페인 포함
    fun getAdvertiserJoinCampaignById(@PathVariable(value = "id") advertiserId: Long
    ): ReadAdvertiserJoinCamapignDTO {
        val advertiser = advertiserRepository.findById(advertiserId)
        val like_adv: String = "%$" + advertiserId + "/%"
        val campaign = campaignRepository.findByAdvertiseridLike(like_adv)
        return ReadAdvertiserJoinCamapignDTO(advertiser.get().id,advertiser.get().name,campaign)
    }


    //광고주 변경
    fun updateAdvertiserById(
        @PathVariable(value = "id") advertiserId: Long,
        createAdvertiserDTO: CreateAdvertiserDTO
    ): String {
        val existingAdvertiser = advertiserRepository.findById(advertiserId)

        return if (existingAdvertiser.isEmpty) {
            "없는 아이디입니다."
        } else {
            var updatedAdvertiser = existingAdvertiser.get().copy(name = createAdvertiserDTO.name)
            val advertiser = advertiserRepository.save(updatedAdvertiser.toCreateAdvertiserDTO().toEnitty())
            advertiser.name
        }
    }

    //광고주 삭제
    fun deleteAdvertiserById(@PathVariable(value = "id") advertiserId: Long): Boolean {
        val advertiser = advertiserRepository.findById(advertiserId)
        return if (advertiser.isEmpty) {
            return false
        } else {
            advertiser.map { deleteAdv -> advertiserRepository.delete(deleteAdv) }

            val checkAdv = advertiserRepository.findById(advertiserId)
            return checkAdv.isEmpty
        }
    }
}