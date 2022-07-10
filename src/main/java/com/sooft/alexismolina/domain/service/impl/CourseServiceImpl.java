package com.sooft.alexismolina.domain.service.impl;

import com.sooft.alexismolina.domain.dto.PageDTO;
import com.sooft.alexismolina.domain.dto.request.CourseRequest;
import com.sooft.alexismolina.domain.dto.response.CourseResponse;
import com.sooft.alexismolina.domain.dto.response.StudentResponse;
import com.sooft.alexismolina.domain.model.Course;
import com.sooft.alexismolina.domain.model.Student;
import com.sooft.alexismolina.domain.repository.CourseRepository;
import com.sooft.alexismolina.domain.repository.StudentRepository;
import com.sooft.alexismolina.domain.service.ICourseService;
import com.sooft.alexismolina.exception.NotFoundException;
import com.sooft.alexismolina.mapper.CourseMapper;
import com.sooft.alexismolina.mapper.StudentMapper;
import com.sooft.alexismolina.util.paginator.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Value("${NotFound.student}")
    private String ErrorStudent;

    @Value("${NotFound.course}")
    private String ErrorCourse;

    @Value("${Course.SortBy.name}")
    private String CourseSort;

    @Override
    public CourseResponse createCourse(CourseRequest request) {
        Course entity = this.courseMapper.requestToEntity(request);
        Course entitySaved = this.courseRepository.save(entity);
        return this.courseMapper.entityToResponse(entitySaved);
    }

    @Override
    public PageDTO<CourseResponse> getAllCoursePageable(int page) {
        PageDTO<CourseResponse> responsePageDTO = new PageDTO<>();
        Page<Course> courses = this.courseRepository.findAll(PageRequest.of(page , Url.MAX_PAGE, Sort.by(CourseSort)));
        return Url.pagination(responsePageDTO,courses,page,this.courseMapper.entityPage2Dto(courses),Url.COURSE_URI);
    }

    @Override
    public StudentResponse addStudent(Long idStudent, Long idCourse) {
        Student entityStudent = this.studentRepository.findById(idStudent).orElseThrow(() -> new NotFoundException(ErrorStudent));
        Course entityCourse = this.courseRepository.findById(idCourse).orElseThrow(() -> new NotFoundException(ErrorCourse));
        entityCourse.addStudent(entityStudent);
        Course entitySaved = this.courseRepository.save(entityCourse);
        return this.studentMapper.entityToResponse(entityStudent);
    }

    @Override
    public CourseResponse updateCourse(Long id, CourseRequest request) {
        Course entityCourse = this.courseRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorCourse));
        this.courseMapper.refreshValues(entityCourse,request);
        Course entitySaved = this.courseRepository.save(entityCourse);
        return this.courseMapper.entityToResponse(entitySaved);
    }

    @Override
    public void deleteCourse(Long id) {
        Course entityCourse = this.courseRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorCourse));
        this.courseRepository.delete(entityCourse);

    }
}
