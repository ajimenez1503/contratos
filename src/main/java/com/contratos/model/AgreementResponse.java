package com.contratos.model;

import lombok.*;

import java.time.Duration;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AgreementResponse {
    private Institute institute;
    private Category category;
    private Double points;
    private Duration duration;
}
