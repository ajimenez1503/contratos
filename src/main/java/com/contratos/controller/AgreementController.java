package com.contratos.controller;

import com.contratos.model.*;
import com.contratos.service.AgreementService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/")
public class AgreementController {

    private AgreementService service;
    private ModelMapper modelMapper;

    @Autowired
    public AgreementController(AgreementService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/agreements", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AgreementResponse>> getAgreements() {
        List<Agreement> agreements = service.getAgreements();
        List<AgreementResponse> agreementResponseList = modelMapper.map(agreements, new TypeToken<List<AgreementResponse>>() {
        }.getType());
        return new ResponseEntity<>(agreementResponseList, HttpStatus.OK);
    }

    @GetMapping(value = "/agreements/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AgreementResponse> getAgreementsById(@PathVariable("id") String id) {
        Optional<Agreement> agreement = service.findAgreementById(Long.parseLong(id));
        if (agreement.isPresent()) {
            AgreementResponse agreementResponse = modelMapper.map(agreement.get(), AgreementResponse.class);
            return new ResponseEntity<>(agreementResponse, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/institutes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Institute>> getInstitutes() {
        return new ResponseEntity<>(service.getInstitute(), HttpStatus.OK);
    }

    @GetMapping(value = "/institutes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Institute> getInstituteById(@PathVariable("id") String id) {
        Optional<Institute> institute = service.findInstituteById(Long.parseLong(id));
        if (institute.isPresent()) {
            return new ResponseEntity<>(institute.get(), HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/categories", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Category>> getCategories() {
        return new ResponseEntity<>(service.getCategories(), HttpStatus.OK);
    }

    @GetMapping(value = "/categories/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> getCategoriesById(@PathVariable("id") String id) {
        Optional<Category> category = service.findCategoryById(id);
        if (category.isPresent()) {
            return new ResponseEntity<>(category.get(), HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/provinces", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<String>> getProvinces() {
        return new ResponseEntity<>(Province.getList(), HttpStatus.OK);
    }

    @PostMapping(value = "/categories", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addCategories(@RequestBody Category category) {
        Category categoryCreated = service.addCategory(category);
        URI location = URI.create(String.format("/api/categories/%s", categoryCreated.getId()));
        return ResponseEntity.created(location).build();
    }

    @PostMapping(value = "/institutes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addInstitute(@RequestBody InstituteDTO instituteDTO) {
        // Check if province is valid
        if (!Province.isValid(instituteDTO.getProvince())) {
            return new ResponseEntity<>("Province is not valid. List of possible: " + Province.getList().toString(), HttpStatus.BAD_REQUEST);
        }
        Institute institute;
        try {
            institute = modelMapper.map(instituteDTO, Institute.class);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.toString(), HttpStatus.BAD_REQUEST);
        }
        Institute instituteCreated = service.addInstitute(institute);
        URI location = URI.create(String.format("/api/institutes/%d", instituteCreated.getId()));
        return ResponseEntity.created(location).build();
    }

    @PostMapping(value = "/agreements", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addAgreement(@RequestBody AgreementRequest request) {
        // check if instituteId exists
        Optional<Institute> institute = service.findInstituteById(request.getInstituteId());
        if (!institute.isPresent()) {
            return new ResponseEntity<>("Institute ID (" + request.getInstituteId() + ") is not valid", HttpStatus.BAD_REQUEST);
        }
        // check if categoryId exists
        Optional<Category> category = service.findCategoryById(request.getCategoryId());
        if (!category.isPresent()) {
            return new ResponseEntity<>("Category ID (" + request.getCategoryId() + ") is not valid", HttpStatus.BAD_REQUEST);
        }
        // Check initial date is before end date.
        if (request.getInitialDate().isAfter(request.getEndDate())) {
            return new ResponseEntity<>("Initial date" + request.getInitialDate().toString() + " is after " + request.getEndDate().toString(), HttpStatus.BAD_REQUEST);
        }

        Agreement agreement = new Agreement();
        agreement.setInstitute(institute.get());
        agreement.setPoints(request.getPoints());
        agreement.setAssignedDate(LocalDate.now(ZoneId.of("Europe/Paris")));
        agreement.setInitialDate(request.getInitialDate());
        agreement.setEndDate(request.getEndDate());
        agreement.setDuration(request.getInitialDate().until(request.getEndDate()));
        agreement.setCategory(category.get());
        Agreement agreementCreated = service.addAgreement(agreement);
        URI location = URI.create(String.format("/api/agreements/%d", agreementCreated.getId()));
        return ResponseEntity.created(location).build();
    }
}
