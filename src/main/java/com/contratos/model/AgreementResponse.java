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
    @NonNull
    private Institute institute;
    @NonNull
    private Category category;
    @NonNull
    private Double points;
    @NonNull
    private Duration duration;
}
