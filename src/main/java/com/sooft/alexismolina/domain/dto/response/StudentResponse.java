package com.sooft.alexismolina.domain.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResponse {
    private Long id;
    private String name;
    private String lastname;
    private String dni;
    private String mail;
}
