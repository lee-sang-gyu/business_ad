package com.business.ad.service

import com.business.ad.dto.CreateAdcontentDTO
import com.business.ad.dto.ReadAdcontentDTO
import com.business.ad.model.Adcontent
import com.business.ad.repository.AdcontentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PathVariable

//서비스: 광고 내용
@Component
class AdcontentService {
    @Autowired
    lateinit var adcontentRepository: AdcontentRepository

    fun getAdcontent(): List<ReadAdcontentDTO> {
        val adcontent = adcontentRepository.findAll()
        return adcontent.map { it.toReadAdcontentDTO() }
    }

    //광고 내용 생성
    @Transactional
    fun createAdcontent(createAdcontentDTO: CreateAdcontentDTO): CreateAdcontentDTO {
        val adcontent = adcontentRepository.save(createAdcontentDTO.toEnitty())
        return adcontent.toCreateAdContentDTO()
    }

    // id로 조회
    fun getAdcontentById(@PathVariable(value = "id") adcontendId: Long): ReadAdcontentDTO {
        val adcontent = adcontentRepository.findById(adcontendId)
        return if (adcontent.isEmpty) {
            ReadAdcontentDTO(adcontendId, "", "", "", "")
        } else {
            adcontent.get().toReadAdcontentDTO()
        }
    }

    // id로 변경
    fun updateAdcontentById(
        @PathVariable(value = "id") adcontentId: Long,
        createAdcontentDTO: CreateAdcontentDTO
    ): ReadAdcontentDTO {
        val existingAdcontent = adcontentRepository.findById(adcontentId)

        return if (existingAdcontent.isEmpty) {
            ReadAdcontentDTO(adcontentId, "", "", "", "")
        } else {
            var updatedAdcontent = existingAdcontent.get().copy(
                content = createAdcontentDTO.content,
                image_url = createAdcontentDTO.image_url,
                btn_text = createAdcontentDTO.btn_text,
                btn_url = createAdcontentDTO.btn_url
            )
            val advertiser = adcontentRepository.save(updatedAdcontent.toCreateAdContentDTO().toEnitty())
            advertiser.toReadAdcontentDTO()
        }
    }

    // id로 삭제
    fun deleteAdcontentById(@PathVariable(value = "id") adcontentId: Long): Boolean {
        val adcontent = adcontentRepository.findById(adcontentId)
        return if (adcontent.isEmpty) {
            return false
        } else {
            adcontent.map { deleteAdcontent -> adcontentRepository.delete(deleteAdcontent) }
            val checkAdcontent = adcontentRepository.findById(adcontentId)
            return checkAdcontent.isEmpty
        }
    }

    //내용 항목으로 조회
    fun getAdcontentByContent(@PathVariable(value = "content") Content: String): List<ReadAdcontentDTO> {
        val adcontent = adcontentRepository.findByContent(Content)
        return adcontent.map { it.toReadAdcontentDTO() }
    }

    //내용 항목만 변경, 나머지의 같은 항목만 변경
    fun updateOnlyContentByContent(
        @PathVariable(value = "content") Content: String,
        readAdcontentDTO: ReadAdcontentDTO
    ): List<ReadAdcontentDTO> {

        val existingAdcontent = adcontentRepository.findByContent(Content)
        return if (existingAdcontent.isEmpty()) {
            existingAdcontent.map { it.toReadAdcontentDTO() }
        } else {
            existingAdcontent.map { it.content = readAdcontentDTO.content }
            val updateAdcontent = adcontentRepository.saveAll(existingAdcontent)
            updateAdcontent.map { it.toReadAdcontentDTO() }
        }
    }

    // 내용 항목 삭제, 나머지의 같은 내용 항목의 광고내용 전부 삭제
    fun deleteOnlyContentByContent(@PathVariable(value = "content") Content: String): Boolean {

        val adcontent = adcontentRepository.findByContent(Content)
        return if (adcontent.isEmpty()) {
            return false
        } else {
            adcontentRepository.deleteAll(adcontent)
            val checkAdcontent = adcontentRepository.findByContent(Content)
            return checkAdcontent.isEmpty()
        }
    }

}