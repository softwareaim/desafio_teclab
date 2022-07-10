package com.sooft.alexismolina.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageDTO<T> {
    private String next;
    private String current;
    private String previous;
    private int total;
    private int totalElements;
    private List<T> contents;

}
