package com.contratos.model;

import lombok.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AgreementResponse {
    @NonNull
    private Institute institute;
    @NonNull
    private Category category;
    @NonNull
    private Double points;
    @NonNull
    private Period duration;
    @NonNull
    private LocalDate initialDate;
    @NonNull
    private LocalDate endDate;
    @NonNull
    private LocalDate assignedDate;
}
