package com.contratos.model;

import lombok.*;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InstituteDTO {
    @NonNull
    private String name;
    @NonNull
    private String address;
    @NonNull
    private String province;
    // link to google map address
}
