package com.sooft.alexismolina.mapper;

import com.sooft.alexismolina.domain.dto.response.StudentResponse;
import com.sooft.alexismolina.domain.model.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
    public StudentResponse entityToResponse(Student entity) {
        StudentResponse response = new StudentResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setDni(entity.getDni());
        response.setLastname(entity.getLastname());
        response.setMail(entity.getMail());
        return response;
    }
}
