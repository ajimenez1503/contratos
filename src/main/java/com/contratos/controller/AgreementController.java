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

import java.time.Duration;
import java.util.Date;
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
        List<AgreementResponse> agreementResponseList = modelMapper.map(service.getAgreements(), new TypeToken<List<AgreementResponse>>() {
        }.getType());
        return new ResponseEntity<>(agreementResponseList, HttpStatus.OK);
    }

    @GetMapping(value = "/institutes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Institute>> getInstitutes() {
        return new ResponseEntity<>(service.getInstitute(), HttpStatus.OK);
    }

    @GetMapping(value = "/categories", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Category>> getCategories() {
        return new ResponseEntity<>(service.getCategories(), HttpStatus.OK);
    }

    @GetMapping(value = "/provinces", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<String>> getProvinces() {
        return new ResponseEntity<>(Province.getList(), HttpStatus.OK);
    }

    @PostMapping(value = "/categories", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addInstitute(@RequestBody Category category) {
        service.addCategory(category);
        return ResponseEntity.accepted().build();
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
        service.addInstitute(institute);
        return ResponseEntity.accepted().build();
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

        Agreement agreement = new Agreement();
        agreement.setInstitute(institute.get());
        agreement.setPoints(request.getPoints());
        agreement.setAssignedDate(new Date(System.currentTimeMillis()));
        agreement.setInitialDate(request.getInitialDate());
        agreement.setEndDate(request.getEndDate());
        agreement.setDuration((Duration.between(request.getEndDate().toInstant(), request.getInitialDate().toInstant())));
        agreement.setCategory(category.get());
        service.addAgreement(agreement);
        return ResponseEntity.accepted().build();
    }
}
