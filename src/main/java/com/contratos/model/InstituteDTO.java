package com.contratos.model;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InstituteDTO {
    @NonNull
    @NotNull
    @NotEmpty
    private String name;
    @NonNull
    @NotNull
    @NotEmpty
    private String address;
    @NonNull
    @NotNull
    @NotEmpty
    private String province;
}
