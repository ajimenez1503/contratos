package com.contratos.model;

import lombok.*;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AgreementRequest {
    private Long instituteId;
    private String categoryId;
    private Double points;
    private Long days;
}
