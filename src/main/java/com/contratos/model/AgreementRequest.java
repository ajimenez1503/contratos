package com.contratos.model;

import lombok.*;

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
    private Long days;
}
