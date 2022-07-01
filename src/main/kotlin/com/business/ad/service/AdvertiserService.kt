package com.business.ad.service

import com.business.ad.dto.CreateAdvertiserDTO
import com.business.ad.dto.ReadAdvertiserDTO
import com.business.ad.repository.AdvertiserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PathVariable

@Component
class AdvertiserService {

    @Autowired
    lateinit var advertiserRepository: AdvertiserRepository

    fun getAdvertisers(): List<ReadAdvertiserDTO> {
        val advertiser = advertiserRepository.findAll()
        return advertiser.map { it.toReadAdvertiserDTO() }
    }

    @Transactional
    fun createAdvertiser(createAdvertiserDTO: CreateAdvertiserDTO): CreateAdvertiserDTO {
        val advertiser = advertiserRepository.save(createAdvertiserDTO.toEnitty())
        return  advertiser.toCreateAdvertiserDTO()
    }

    fun getAdvertiserById(@PathVariable(value = "id") advertiserId: Long): ReadAdvertiserDTO {
        val advertiser = advertiserRepository.findById(advertiserId)

        return if (advertiser.isEmpty) {
            ReadAdvertiserDTO(advertiserId,"")
        } else {
            advertiser.get().toReadAdvertiserDTO()
        }
    }

    fun updateAdvertiserById(@PathVariable(value = "id") advertiserId: Long,
                             createAdvertiserDTO: CreateAdvertiserDTO
    ): String {
        val existingAdvertiser = advertiserRepository.findById(advertiserId)
        var updatedAdvertiser = existingAdvertiser.get().copy(name = createAdvertiserDTO.name)
        val advertiser = advertiserRepository.save(updatedAdvertiser.toCreateAdvertiserDTO().toEnitty())
        return if (existingAdvertiser.isEmpty) {
            "없는 아이디입니다."
        } else {
            advertiser.name
        }
    }
    /*@DeleteMapping("/advertiser/{id}")
    fun deleteAdvertiserById(@PathVariable(value = "id") advertiserId: Long): ResponseEntity<Void> {

        return advertiserRepository.findById(advertiserId).map { advertiser ->
            advertiserRepository.delete(advertiser)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())

    }*/
    fun deleteAdvertiserById(@PathVariable(value = "id") advertiserId: Long): Boolean {

        val advertiser = advertiserRepository.findById(advertiserId)
        advertiser.map { deleteAdv -> advertiserRepository.delete(deleteAdv) }

        val checkAdv = advertiserRepository.findById(advertiserId)
        return checkAdv.isEmpty
       /* return advertiserRepository.findById(advertiserId).map { advertiser ->
            advertiserRepository.delete(advertiser)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())*/
    }
}