package com.contratos.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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
    private AgreementDurationType durationType;
    @NonNull
    @NotNull
    @FutureOrPresent
    private LocalDate initialDate;
    @NonNull
    @NotNull
    @FutureOrPresent
    private LocalDate endDate;
    @NonNull
    @NotNull
    private Boolean accepted;
}
