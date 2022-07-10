package com.sooft.alexismolina.util.paginator;


import com.sooft.alexismolina.domain.dto.PageDTO;
import com.sooft.alexismolina.exception.NotFoundException;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class Url {

    public static final String TICKETS_URI = "/tickets";
    public static final String COMMENTS_URI = "/comments";
    public static final String COURSE_URI = "/course";
    public static final String URL = "http://localhost:8080";
    public static final String PAGE_URI = "/?page=";
    public static final int MAX_PAGE = 5;

    public static PageDTO pagination(PageDTO pageDTO, Page pageEntity, Integer page, List entityPage2Dto, @NotEmpty String url) {
        if (pageEntity.isEmpty()) {
            throw new NotFoundException("The list is empty");
        }
        if (pageEntity.hasNext()) {
            pageDTO.setNext(URL + url + PAGE_URI + (page + 1));
        }
        if (!pageEntity.isFirst()) {
            pageDTO.setPrevious(URL + url + PAGE_URI + (page - 1));
        }
        pageDTO.setCurrent(Integer.toString(page));
        pageDTO.setContents(entityPage2Dto);
        pageDTO.setTotal(pageEntity.getTotalPages());
        pageDTO.setTotalElements(pageEntity.getNumberOfElements());
        return pageDTO;
    }


}
