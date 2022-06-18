package com.contratos.model;

import lombok.*;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InstituteDTO {
    private String name;
    private String address;
    // google map address link
    private String province;
}
