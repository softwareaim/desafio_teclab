package com.sooft.alexismolina.controller;

import com.sooft.alexismolina.controller.api.CourseApi;
import com.sooft.alexismolina.domain.dto.PageDTO;
import com.sooft.alexismolina.domain.dto.request.CourseRequest;
import com.sooft.alexismolina.domain.dto.response.CourseResponse;
import com.sooft.alexismolina.domain.dto.response.StudentResponse;
import com.sooft.alexismolina.domain.service.ICourseService;
import com.sooft.alexismolina.exception.BadRequestException;
import com.sooft.alexismolina.util.paginator.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = Url.COURSE_URI)
public class CourseController implements CourseApi {

    @Autowired
    private ICourseService courseService;

    @PostMapping
    public ResponseEntity<CourseResponse> createCourse(@Valid @RequestBody CourseRequest request, BindingResult result) {
        if (result.hasErrors()) {
            throw new BadRequestException(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(this.courseService.createCourse(request));
    }

    @GetMapping
    public ResponseEntity<PageDTO<CourseResponse>> getAllCoursePageable(
            @RequestParam(name = "page", defaultValue = "0") int page) {
        return ResponseEntity.ok().body(this.courseService.getAllCoursePageable(page));
    }

    @PutMapping(value = "/{idCourse}/addStudent/{idStudent}")
    public ResponseEntity<StudentResponse> addStudentCourse(@PathVariable Long idCourse, @PathVariable Long idStudent) {
        return ResponseEntity.ok().body(this.courseService.addStudent(idCourse, idStudent));
    }

    @PutMapping(value = "/{idCourse}")
    public ResponseEntity<CourseResponse> updateCourse(@PathVariable Long idCourse,
                                                       @Valid @RequestBody CourseRequest request, BindingResult result) {
        if (result.hasErrors()) {
            throw new BadRequestException(result);
        }
        return ResponseEntity.ok().body(this.courseService.updateCourse(idCourse, request));
    }

    @DeleteMapping(value = "/{idCourse}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long idCourse) {
        this.courseService.deleteCourse(idCourse);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
