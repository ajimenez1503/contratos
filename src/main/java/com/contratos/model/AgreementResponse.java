package com.contratos.model;

import lombok.*;

import java.time.Duration;
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
    private Duration duration;
    @NonNull
    private Date initialDate;
    @NonNull
    private Date endDate;
    @NonNull
    private Date assignedDate;
}
