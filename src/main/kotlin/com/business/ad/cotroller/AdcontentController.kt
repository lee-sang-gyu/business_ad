package com.business.ad.cotroller

import com.business.ad.dto.CreateAdcontentDTO
import com.business.ad.dto.CreateAdvertiserDTO
import com.business.ad.dto.ReadAdcontentDTO
import com.business.ad.dto.ReadAdvertiserDTO
import com.business.ad.service.AdcontentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

//컨트롤러: 광고 내용
@RestController
@RequestMapping("/api")
class AdcontentController {
    @Autowired
    private lateinit var adcontentService: AdcontentService

    @GetMapping("/adcontent")
    fun getAllAdcontent(): ResponseEntity<Any> {
        return ResponseEntity.ok().body(adcontentService.getAdcontent())
    }

    //광고 내용 생성
    @PostMapping("/adcontent")
    fun createAdcontent(@RequestBody createAdcontentDTO: CreateAdcontentDTO)
            : ResponseEntity<Any> {
        adcontentService.createAdcontent(createAdcontentDTO)
        return ResponseEntity.ok().body(true)
    }

    //Id로 조회
    @GetMapping("/adcontent/id/{id}")
    fun getAdvertiserById(@PathVariable(value = "id") adcontentId: Long)
            : ResponseEntity<ReadAdcontentDTO> {
        return ResponseEntity.ok().body(adcontentService.getAdcontentById(adcontentId))
    }

    //Id로 변경
    @PutMapping("/adcontent/id/{id}")
    fun updateAdcontentById(
        @PathVariable(value = "id") adcontentId: Long,
        @RequestBody updateAdcontent: CreateAdcontentDTO
    ): ResponseEntity<Any> {
        return ResponseEntity.ok().body(adcontentService.updateAdcontentById(adcontentId, updateAdcontent))
    }

    //Id로 삭제
    @DeleteMapping("/adcontent/id/{id}")
    fun deleteAdcontentById(@PathVariable(value = "id") adcontentId: Long)
            : ResponseEntity<Any> {
        return ResponseEntity.ok().body(adcontentService.deleteAdcontentById(adcontentId))
    }

    //내용 항목으로 조회
    @GetMapping("/adcontent/{content}")
    fun getAdcontentByContent(@PathVariable(value = "content") adContent: String)
            : ResponseEntity<Any> {
        return ResponseEntity.ok().body(adcontentService.getAdcontentByContent(adContent))
    }

    //내용 항목으로 변경
    @PutMapping("/adcontent/{content}")
    fun updateOnlyContentByContent(
        @PathVariable(value = "content") adContent: String,
        @RequestBody updateAdcontent: ReadAdcontentDTO
    ): ResponseEntity<Any> {
        return ResponseEntity.ok().body(adcontentService.updateOnlyContentByContent(adContent, updateAdcontent))
    }

    //내용 항목으로 삭제
    @DeleteMapping("/adcontent/{content}")
    fun deleteOnlyContentByContent(
        @PathVariable(value = "content") adContent: String
    ): ResponseEntity<Any> {
        return ResponseEntity.ok().body(adcontentService.deleteOnlyContentByContent(adContent))
    }
}