package com.contratos.model;

import lombok.*;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AgreementRequest {
    @NonNull
    @NotNull
    private Long instituteId;
    @NonNull
    @NotNull
    @NotEmpty
    private String categoryId;
    @NonNull
    @NotNull
    private Double points;
    @NonNull
    @NotNull
    @FutureOrPresent
    private LocalDate initialDate;
    @NonNull
    @NotNull
    @FutureOrPresent
    private LocalDate endDate;
}
