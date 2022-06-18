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
    @NonNull
    private Long id;
    @OneToOne
    @NonNull
    private Institute institute;
    @OneToOne
    @NonNull
    private Category category;
    @NonNull
    private Double points;
    @NonNull
    private Duration duration;
}
