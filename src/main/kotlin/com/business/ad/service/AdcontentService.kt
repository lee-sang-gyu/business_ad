package com.business.ad.service

import com.business.ad.dto.CreateAdcontentDTO
import com.business.ad.dto.ReadAdcontentDTO
import com.business.ad.error.NotFoundException
import com.business.ad.model.Adcontent
import com.business.ad.repository.AdcontentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PathVariable

//서비스: 광고 내용
@Component
class AdcontentService {
    @Autowired
    lateinit var adcontentRepository: AdcontentRepository

    //광고 내용 전체 조회
    fun getAdcontent(): List<ReadAdcontentDTO> {
        val adcontent = adcontentRepository.findAll()
        return adcontent.map { it.toReadAdcontentDTO() }
    }

    //광고 내용 생성
    fun createAdcontent(createAdcontentDTO: CreateAdcontentDTO): CreateAdcontentDTO {
        val adcontent = adcontentRepository.save(createAdcontentDTO.toEnitty())
        return adcontent.toCreateAdContentDTO()
    }

    // id로 조회
    fun getAdcontentById(@PathVariable(value = "id") adcontendId: Long): ReadAdcontentDTO {

        val adcontent = findAdcontentByIdOrThrowNotFound(adcontendId)
        return adcontent.toReadAdcontentDTO()

    }

    // id로 변경
    fun updateAdcontentById(
        @PathVariable(value = "id") adcontentId: Long,
        createAdcontentDTO: CreateAdcontentDTO
    ): ReadAdcontentDTO {
        val existingAdcontent = findAdcontentByIdOrThrowNotFound(adcontentId)

        var updatedAdcontent = existingAdcontent.copy(
            content = if (createAdcontentDTO.content == null) {
                existingAdcontent.content
            } else {
                createAdcontentDTO.content
            },
            image_url = if (createAdcontentDTO.image_url == null) {
                existingAdcontent.image_url
            } else {
                createAdcontentDTO.image_url
            },
            btn_text = if (createAdcontentDTO.btn_text == null) {
                existingAdcontent.btn_text
            } else {
                createAdcontentDTO.btn_text
            },
            btn_url = if (createAdcontentDTO.btn_url == null) {
                existingAdcontent.btn_url
            } else {
                createAdcontentDTO.btn_url
            }
        )
        val advertiser = adcontentRepository.save(updatedAdcontent.toCreateAdContentDTO().toEnitty())
        return advertiser.toReadAdcontentDTO()
    }

    // id로 삭제
    fun deleteAdcontentById(@PathVariable(value = "id") adcontentId: Long) {
        val adcontent = findAdcontentByIdOrThrowNotFound(adcontentId)
        adcontentRepository.delete(adcontent)
    }

    //내용 항목으로 조회
    fun getAdcontentByContent(@PathVariable(value = "content") Content: String): List<ReadAdcontentDTO> {
        val adcontent = findAdcontentByContentOrThrowNotFound(Content)
        return adcontent.map { it.toReadAdcontentDTO() }
    }

    //내용 항목만 변경, 나머지의 같은 항목만 변경
    fun updateOnlyContentByContent(
        @PathVariable(value = "content") Content: String,
        readAdcontentDTO: ReadAdcontentDTO
    ): List<ReadAdcontentDTO> {

        val existingAdcontent = findAdcontentByContentOrThrowNotFound(Content)
        existingAdcontent.map { it.content = readAdcontentDTO.content }
        val updateAdcontent = adcontentRepository.saveAll(existingAdcontent)
        return updateAdcontent.map { it.toReadAdcontentDTO() }
    }

    // 내용 항목 삭제, 나머지의 같은 내용 항목의 광고내용 전부 삭제
    fun deleteOnlyContentByContent(@PathVariable(value = "content") Content: String) {

        val adcontent = findAdcontentByContentOrThrowNotFound(Content)
        adcontentRepository.deleteAll(adcontent)
    }

    private fun findAdcontentByIdOrThrowNotFound(id: Long): Adcontent {
        val adcontent = adcontentRepository.findByIdOrNull(id)
        if (adcontent == null) {
            throw NotFoundException()
        } else {
            return adcontent;
        }
    }

    private fun findAdcontentByContentOrThrowNotFound(content: String): List<Adcontent> {
        val adcontent = adcontentRepository.findByContent(content)
        if (adcontent == null) {
            throw NotFoundException()
        } else {
            return adcontent;
        }
    }
}