package com.business.ad.dto

import com.business.ad.model.Result
import java.util.*

//DTO: 켐페인 결과
data class ReadResultDTO(
    val id: Long? = null,
    val campaignid: Long,
    val cliks: Int?,
    val exposure: Int?
)

data class CreateResultDTO(
    val id: Long,
    val campaignid: Long,
    val cliks: Int,
    val exposure: Int
) {
    fun toEnitty(): Result {
        return Result(
            id = id,
            campaignid = campaignid,
            cliks = cliks,
            exposure = exposure
        )
    }
}