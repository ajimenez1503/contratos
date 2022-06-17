package com.contratos.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

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
