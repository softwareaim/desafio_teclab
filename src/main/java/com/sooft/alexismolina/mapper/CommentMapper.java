package com.sooft.alexismolina.mapper;

import com.sooft.alexismolina.domain.model.Comment;
import com.sooft.alexismolina.domain.dto.response.CommentResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommentMapper {

    public CommentResponse entityToCommentDto(Comment entity) {
        CommentResponse response = new CommentResponse();
        response.setId(entity.getId());
        response.setBody(entity.getBody());
        response.setCreateDateTime(entity.getCreateDateTime());
        return response;
    }

    public List<CommentResponse> entitiesPage2Dto(Page<Comment> entities) {
        List<CommentResponse> dtos = new ArrayList<>();
        entities.getContent().forEach(entity -> dtos.add(this.entityToCommentDto(entity)));
        return dtos;
    }


}
