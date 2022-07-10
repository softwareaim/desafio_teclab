package com.sooft.alexismolina.domain.dto.response;

import com.sooft.alexismolina.enums.Priority;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TicketResponse {

    private Long id;
    private String subject;
    private Priority priority;
    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;
}
