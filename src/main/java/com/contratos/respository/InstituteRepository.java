package com.contratos.respository;

import com.contratos.model.Institute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface InstituteRepository extends JpaRepository<Institute, Long> {
    @Query("SELECT province FROM Institute institute")
    Set<String> findProvinces();

    @Query("SELECT kind FROM Institute institute")
    Set<String> findKinds();
}
