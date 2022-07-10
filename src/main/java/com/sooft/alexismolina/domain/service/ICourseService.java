package com.sooft.alexismolina.domain.service;

import com.sooft.alexismolina.domain.dto.PageDTO;
import com.sooft.alexismolina.domain.dto.request.CourseRequest;
import com.sooft.alexismolina.domain.dto.response.CourseResponse;
import com.sooft.alexismolina.domain.dto.response.StudentResponse;
import org.springframework.transaction.annotation.Transactional;

public interface ICourseService {

    @Transactional
    CourseResponse createCourse(CourseRequest request);

    @Transactional(readOnly = true)
    PageDTO<CourseResponse> getAllCoursePageable(int page);

    @Transactional
    StudentResponse addStudent (Long idStudent,Long idCourse);

    @Transactional
    CourseResponse updateCourse(Long id,CourseRequest request);

    @Transactional
    void deleteCourse(Long id);
}
