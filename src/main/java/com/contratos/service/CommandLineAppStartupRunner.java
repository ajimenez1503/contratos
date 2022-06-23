package com.contratos.service;

import com.contratos.model.Category;
import com.contratos.model.Institute;
import com.contratos.model.Province;
import com.contratos.respository.CategoryRepository;
import com.contratos.respository.InstituteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    private static final Logger LOG =
            LoggerFactory.getLogger(CommandLineAppStartupRunner.class);

    private InstituteRepository instituteRepository;

    private CategoryRepository categoryRepository;

    @Autowired
    public CommandLineAppStartupRunner(InstituteRepository instituteRepository, CategoryRepository categoryRepository) {
        this.instituteRepository = instituteRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        LOG.info("Initialize categories and institutes tables");
        categoryRepository.save(new Category("TCAE", "Auxiliar de Enfermería"));
        categoryRepository.save(new Category("DUE", "Enfermería"));
        categoryRepository.save(new Category("TER", "Tecnico en Ambulancia"));
        categoryRepository.save(new Category("FEA", "Facultativo Especialista Adjunto"));

        instituteRepository.save(new Institute(1L, "Centro de salud Abla", "AV SANTOS MARTIRES, 22 04510 ABLA Abla Almería", Province.ALMERIA));
        instituteRepository.save(new Institute(2L, "Consultorio Armuña de Almanzora", "CL ERAS, 18 04888 ARMUÑA DE ALMANZORA Armuña de Almanzora Almería", Province.ALMERIA));
    }
}