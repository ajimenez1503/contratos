package com.contratos.model;

import lombok.*;

import javax.persistence.*;
import java.time.Duration;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AgreementResponse {
    private Institute institute;
    private Double points;
    private Duration duration;

}
