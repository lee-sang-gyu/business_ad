package com.business.ad.model

import com.business.ad.dto.CreateAdvertiserDTO
import com.business.ad.dto.ReadAdvertiserDTO
import javax.persistence.*
import javax.validation.constraints.NotBlank

//모델: 광고주 정보
@Suppress("JpaAttributeTypeInspection")
@Entity
data class Advertiser(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @get: NotBlank
    var name: String = ""
) {
    fun toReadAdvertiserDTO(): ReadAdvertiserDTO {
        return ReadAdvertiserDTO(
            id = id,
            name = name
        )
    }

    fun toCreateAdvertiserDTO(): CreateAdvertiserDTO {
        return CreateAdvertiserDTO(
            id = id,
            name = name
        )
    }
}

/*
package com.business.ad.entity

import javax.persistence.*

@Entity
@Table(name = "company")
class Company(name: String) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    var name: String = name

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Company

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "Company(id=$id, name='$name')"
    }
}*/