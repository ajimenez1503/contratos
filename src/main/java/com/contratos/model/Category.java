package com.contratos.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Category {
    @Id
    @NonNull
    @NotNull
    @NotEmpty
    private String id;
    @NonNull
    @NotNull
    @NotEmpty
    private String fullName;
}
