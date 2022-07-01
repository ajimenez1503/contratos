package com.contratos.controller;

import com.contratos.model.*;
import com.contratos.service.AgreementService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = "http://localhost:4200")
public class AgreementController {

    private final AgreementService service;
    private final ModelMapper modelMapper;

    @Autowired
    public AgreementController(AgreementService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/agreements", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Agreement>> getAgreements() {
        return new ResponseEntity<>(service.getAgreements(), HttpStatus.OK);
    }

    @GetMapping(value = "/agreements/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Agreement> getAgreementsById(@PathVariable(value = "id", required = true) String id) {
        Optional<Agreement> agreement = service.findAgreementById(Long.parseLong(id));
        if (agreement.isPresent()) {
            return new ResponseEntity<>(agreement.get(), HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/institutes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Institute>> getInstitutes(@RequestParam(required = false) String province, @RequestParam(required = false) String kind) {
        if ((province == null || province.isEmpty()) && (kind == null || kind.isEmpty())) {
            return new ResponseEntity<>(service.getInstitute(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(service.getInstituteBy(province, kind), HttpStatus.OK);
        }
    }

    @GetMapping(value = "/institutes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Institute> getInstituteById(@PathVariable(value = "id", required = true) String id) {
        Optional<Institute> institute = service.findInstituteById(Long.parseLong(id));
        if (institute.isPresent()) {
            return new ResponseEntity<>(institute.get(), HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/institutes/provinces", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<String>> getInstitutesProvinces() {
        return new ResponseEntity<>(service.getInstitutesProvinces(), HttpStatus.OK);
    }

    @GetMapping(value = "/institutes/kinds", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<String>> getInstitutesKinds() {
        return new ResponseEntity<>(service.getInstitutesKinds(), HttpStatus.OK);
    }

    @GetMapping(value = "/categories", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Category>> getCategories() {
        return new ResponseEntity<>(service.getCategories(), HttpStatus.OK);
    }

    @GetMapping(value = "/categories/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> getCategoriesById(@PathVariable(value = "id", required = true) String id) {
        Optional<Category> category = service.findCategoryById(id);
        if (category.isPresent()) {
            return new ResponseEntity<>(category.get(), HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/categories", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addCategories(@Valid @RequestBody Category category) {
        Category categoryCreated = service.addCategory(category);
        URI location = URI.create(String.format("/api/categories/%s", categoryCreated.getId()));
        return ResponseEntity.created(location).build();
    }

    @PostMapping(value = "/institutes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addInstitute(@Valid @RequestBody InstituteDTO instituteDTO) {
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
    public ResponseEntity<String> addAgreement(@Valid @RequestBody AgreementRequest request) {
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
            return new ResponseEntity<>("Initial date '" + request.getInitialDate() + "' is after '" + request.getEndDate() + "'", HttpStatus.BAD_REQUEST);
        }

        Agreement agreement = new Agreement();
        agreement.setInstitute(institute.get());
        agreement.setPoints(request.getPoints());
        agreement.setAssignedDate(LocalDate.now(ZoneId.of("Europe/Paris")));
        agreement.setInitialDate(request.getInitialDate());
        agreement.setEndDate(request.getEndDate());
        agreement.setDuration(request.getInitialDate().until(request.getEndDate()));
        agreement.setCategory(category.get());
        agreement.setDurationType(request.getDurationType());
        agreement.setAccepted((request.getAccepted()));
        Agreement agreementCreated = service.addAgreement(agreement);
        URI location = URI.create(String.format("/api/agreements/%d", agreementCreated.getId()));
        return ResponseEntity.created(location).build();
    }
}
