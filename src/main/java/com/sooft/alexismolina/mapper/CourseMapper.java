package com.sooft.alexismolina.mapper;

import com.sooft.alexismolina.domain.dto.request.CourseRequest;
import com.sooft.alexismolina.domain.dto.response.CourseResponse;
import com.sooft.alexismolina.domain.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Component
public class CourseMapper {
    public Course requestToEntity(@NotNull CourseRequest request) {
        Course entity = new Course();
        refreshValues(entity,request);
        return entity;
    }

    public CourseResponse entityToResponse(Course entity) {
        CourseResponse response = new CourseResponse();
        response.setId(entity.getId());
        response.setTeacherName(entity.getTeacherName());
        response.setName(entity.getName());
        return response;
    }

    public List<CourseResponse> entityPage2Dto(Page<Course> courses) {
        List<CourseResponse> responseList = new ArrayList<>();
        courses.getContent().forEach(entity -> responseList.add(this.entityToResponse(entity)));
        return responseList;
    }

    public void refreshValues(Course entity, @NotNull CourseRequest request){
        entity.setName(request.getName());
        entity.setTeacherName(request.getTeacherName());
    }
}
