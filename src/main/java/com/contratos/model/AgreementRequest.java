package com.contratos.model;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AgreementRequest {
    @NonNull
    private Long instituteId;
    @NonNull
    private String categoryId;
    @NonNull
    private Double points;
    @NonNull
    private LocalDate initialDate;
    @NonNull
    private LocalDate endDate;
}
