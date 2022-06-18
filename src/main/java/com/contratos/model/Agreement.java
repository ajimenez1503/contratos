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
@Entity
public class Agreement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Institute institute;
    @OneToOne
    private Category category;
    private Double points;
    private Duration duration;
}
