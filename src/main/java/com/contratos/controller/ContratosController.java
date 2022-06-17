package com.contratos.controller;

import com.contratos.model.*;
import com.contratos.service.ContratosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import javax.xml.datatype.Duration;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.time.Duration.ofDays;

@RestController
@RequestMapping("/api/")
public class ContratosController {

    private ContratosService service;
    private ModelMapper modelMapper;

    @Autowired
    public ContratosController(ContratosService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/status")
    public ResponseEntity<String> status() {
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/agreements", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AgreementResponse>> getAgreements() {
        List<AgreementResponse> agreementResponseList = modelMapper.map(service.getAgreements(), new TypeToken<List<AgreementResponse>>() {
        }.getType());
        return new ResponseEntity<>(agreementResponseList, HttpStatus.OK);
    }

    @GetMapping(value = "/institutes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Institute>> getInstitute() {
        return new ResponseEntity<>(service.getInstitute(), HttpStatus.OK);
    }

    @PostMapping(value = "/agreements", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addAgreement(@RequestBody AgreementRequest request) {
        // check if instituteID exists
        Optional<Institute> institute = service.findInstituteById(request.getInstituteId());
        if (!institute.isPresent()) {
            return new ResponseEntity<>("Institute ID is not valid", HttpStatus.BAD_REQUEST);
        }

        Agreement agreement= new Agreement();
        agreement.setInstitute(institute.get());
        agreement.setPoints(request.getPoints());
        agreement.setDuration(ofDays(request.getDays()));
        service.addAgreement(agreement);
        return ResponseEntity.accepted().build();
    }

    @GetMapping(value = "/provinces", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<String>> getProvinces() {
        return new ResponseEntity<>(Province.getList(), HttpStatus.OK);
    }

    @PostMapping(value = "/institutes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addInstitute(@RequestBody InstituteDTO instituteDTO) {
        // Check if province is valid
        if (!Province.isValid(instituteDTO.getProvince())) {
            return new ResponseEntity<>("Province is not valid. List of possible: " + Province.getList().toString(), HttpStatus.BAD_REQUEST);
        }
        Institute institute = modelMapper.map(instituteDTO, Institute.class);
        service.addInstitute(institute);
        return ResponseEntity.accepted().build();
    }
}
