package com.contratos.respository;

import com.contratos.model.Institute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface InstituteRepository extends JpaRepository<Institute, Long> {
    @Query("SELECT province FROM Institute institute")
    Set<String> findProvinces();

    @Query("SELECT kind FROM Institute institute")
    Set<String> findKinds();

    List<Institute> findByProvinceAndKind(String province, String kind);

    List<Institute> findByProvince(String province);

    List<Institute> findByKind(String kind);

    @Query("SELECT kind FROM Institute institute where institute.province = ?1")
    Set<String> findKindsByProvince(String province);

    @Query("SELECT province FROM Institute institute where institute.kind = ?1")
    Set<String> findProvincesByKind(String kind);
}
