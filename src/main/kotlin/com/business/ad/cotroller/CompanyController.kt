package com.business.ad.cotroller

import com.business.ad.entity.Company
import com.business.ad.repository.CompanyRepository


import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class CompanyController(private val companyRepository: CompanyRepository) {

    @GetMapping("/company")
    fun getAllCompany(): List<Company> =
        companyRepository.findAll()

    @PostMapping("/company")
    fun createNewCompany(@Valid @RequestBody company: Company): Company =
        companyRepository.save(company)

    @GetMapping("/company/{id}")
    fun getCompanyById(@PathVariable(value = "id") companyId: Long): ResponseEntity<Company> {
        return companyRepository.findById(companyId).map { company ->
            ResponseEntity.ok(company)
        }.orElse(ResponseEntity.notFound().build())
    }
    @PutMapping("/company/{id}")
    fun updateCompanyById(@PathVariable(value = "id") companyId: Long,
                          @Valid @RequestBody newCompany: Company): ResponseEntity<Company> {

        return companyRepository.findById(companyId).map { existingCompany ->
            val updatedCompany: Company = existingCompany
                .copy(name = newCompany.name)
            ResponseEntity.ok().body(companyRepository.save(updatedCompany))
        }.orElse(ResponseEntity.notFound().build())

    }

    @DeleteMapping("/company/{id}")
    fun deleteCompanyById(@PathVariable(value = "id") companyId: Long): ResponseEntity<Void> {

        return companyRepository.findById(companyId).map { company  ->
            companyRepository.delete(company)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())

    }

}