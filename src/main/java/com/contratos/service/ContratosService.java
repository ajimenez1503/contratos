package com.contratos.service;


import com.contratos.model.Agreement;
import com.contratos.model.Institute;
import com.contratos.respository.AgreementRepository;
import com.contratos.respository.InstituteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ContratosService {
    private AgreementRepository agreementRepository;
    private InstituteRepository instituteRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(ContratosService.class);

    @Autowired
    public ContratosService(AgreementRepository agreementRepository, InstituteRepository instituteRepository) {
        this.agreementRepository = agreementRepository;
        this.instituteRepository = instituteRepository;
    }

    public List<Agreement> getAgreements() {
        LOGGER.info("GET Agreements");
        return agreementRepository.findAll();
    }

    public List<Institute> getInstitute() {
        LOGGER.info("GET Institute");
        return instituteRepository.findAll();
    }

    public Agreement addAgreement(Agreement agreement) {
        return agreementRepository.save(agreement);
    }

    public Institute addInstitute(Institute institute) {
        return instituteRepository.save(institute);
    }

    public void deleteAgreement(Agreement agreement) {
        agreementRepository.delete(agreement);
    }

    public void deleteInstitute(Institute institute) {
        instituteRepository.delete(institute);
    }

    public Optional<Institute> findInstituteById(Long instituteId) {
        return instituteRepository.findById(instituteId);
    }
}
