package com.business.ad.model

import com.business.ad.dto.CreateResultDTO
import com.business.ad.dto.ReadResultDTO
import javax.persistence.*

//모델: 캠페인 결과
@Entity
data class Result(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    var campaignId: Long,
    var cliks: Int,
    var exposure: Int/*,
    @OneToOne(orphanRemoval = false)
    val campaign: Campaign*/
){
    fun toReadResultDTO(): ReadResultDTO {
        return ReadResultDTO(
            id = id,
            campaignId = campaignId,
            cliks = cliks,
            exposure = exposure
        )
    }

    fun toCreateResultDTO(): CreateResultDTO {
        return CreateResultDTO(
            id = id,
            campaignId = campaignId,
            cliks = cliks,
            exposure = exposure
        )
    }
}
