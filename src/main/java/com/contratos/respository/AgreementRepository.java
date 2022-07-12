package com.contratos.respository;

import com.contratos.model.Agreement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AgreementRepository extends JpaRepository<Agreement, Long> {
    @Query("SELECT a FROM Agreement a where a.category.id like %:category%")
    List<Agreement> search(
            @Param("category") String requestCategory);
}
