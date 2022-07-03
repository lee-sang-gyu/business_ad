package com.business.ad.model

import com.business.ad.dto.CreateResultDTO
import com.business.ad.dto.ReadResultDTO
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

//모델: 캠페인 결과
@Entity
data class Result(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val campaignid: Long,
    var cliks: Int,
    var exposure: Int
){
    fun toReadResultDTO(): ReadResultDTO {
        return ReadResultDTO(
            id = id,
            campaignid = campaignid,
            cliks = cliks,
            exposure = exposure
        )
    }

    fun toCreateResultDTO(): CreateResultDTO {
        return CreateResultDTO(
            id = id,
            campaignid = campaignid,
            cliks = cliks,
            exposure = exposure
        )
    }
}
