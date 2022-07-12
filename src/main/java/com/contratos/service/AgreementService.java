package com.contratos.service;


import com.contratos.model.Agreement;
import com.contratos.model.Category;
import com.contratos.model.Institute;
import com.contratos.respository.AgreementRepository;
import com.contratos.respository.CategoryRepository;
import com.contratos.respository.InstituteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AgreementService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AgreementService.class);
    private final AgreementRepository agreementRepository;
    private final InstituteRepository instituteRepository;

    private final CategoryRepository categoryRepository;

    @Autowired
    public AgreementService(AgreementRepository agreementRepository, InstituteRepository instituteRepository, CategoryRepository categoryRepository) {
        this.agreementRepository = agreementRepository;
        this.instituteRepository = instituteRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Agreement> getAgreementsBy(String Category) {
        String requestCategory = "";
        if (Category != null && !Category.isEmpty()) {
            requestCategory = Category;
        }
        LOGGER.info("GET Agreements by category '{}'", requestCategory);
        return agreementRepository.search(requestCategory);
    }

    public List<Category> getCategories() {
        LOGGER.info("GET Categories");
        return categoryRepository.findAll();
    }

    public Agreement addAgreement(Agreement agreement) {
        LOGGER.info("POST Agreements '{}'", agreement);
        return agreementRepository.save(agreement);
    }

    public Institute addInstitute(Institute institute) {
        LOGGER.info("POST Institute '{}'", institute);
        return instituteRepository.save(institute);
    }

    public Category addCategory(Category category) {
        LOGGER.info("POST Category '{}'", category);
        return categoryRepository.save(category);
    }

    public void deleteAgreement(Agreement agreement) {
        LOGGER.info("DELETE Agreements '{}'", agreement);
        agreementRepository.delete(agreement);
    }

    public void deleteInstitute(Institute institute) {
        LOGGER.info("DELETE Institute '{}'", institute);
        instituteRepository.delete(institute);
    }

    public void deleteCategory(Category category) {
        LOGGER.info("DELETE Category '{}'", category);
        categoryRepository.delete(category);
    }

    public Optional<Institute> findInstituteById(Long instituteId) {
        LOGGER.info("GET Institute by Id '{}'", instituteId);
        return instituteRepository.findById(instituteId);
    }

    public Optional<Category> findCategoryById(String categoryId) {
        LOGGER.info("GET Category by Id '{}'", categoryId);
        return categoryRepository.findById(categoryId);
    }

    public Optional<Agreement> findAgreementById(Long agreementId) {
        LOGGER.info("GET Agreement by Id '{}'", agreementId);
        return agreementRepository.findById(agreementId);
    }

    public Set<String> getInstitutesProvincesBy(String kind) {
        if (kind != null && !kind.isEmpty()) {
            LOGGER.info("Find all the Institute provinces by kind '{}'", kind);
            return instituteRepository.findProvincesByKind(kind);
        } else {
            LOGGER.info("Find all the Institute provinces");
            return instituteRepository.findProvinces();
        }
    }

    public Set<String> getInstitutesKindsBy(String province) {
        if (province != null && !province.isEmpty()) {
            LOGGER.info("Find all the Institute kind by province '{}'", province);
            return instituteRepository.findKindsByProvince(province);
        } else {
            LOGGER.info("Find all the Institute kinds");
            return instituteRepository.findKinds();
        }
    }

    public List<Institute> getInstituteBy(String province, String kind) {
        if (province != null && !province.isEmpty() && kind != null && !kind.isEmpty()) {
            LOGGER.info("Find all the Institute by province '{}' and kind '{}'", province, kind);
            return instituteRepository.findByProvinceAndKind(province, kind);
        } else if (province != null && !province.isEmpty()) {
            LOGGER.info("Find all the Institute by province '{}'", province);
            return instituteRepository.findByProvince(province);
        } else if (kind != null && !kind.isEmpty()) {
            LOGGER.info("Find all the Institute by kind '{}'", kind);
            return instituteRepository.findByKind(kind);
        } else {
            LOGGER.info("Find all the Institute");
            return instituteRepository.findAll();
        }
    }
}
