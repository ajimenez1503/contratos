package com.contratos.model;

import lombok.*;

import java.time.Duration;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AgreementRequest {
    private Long instituteId;
    private Double points;
    private Long days;
}
