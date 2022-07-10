package com.sooft.alexismolina.mapper;

import com.sooft.alexismolina.domain.model.Ticket;
import com.sooft.alexismolina.domain.dto.response.TicketResponse;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class TicketMapper {

    public TicketResponse entityToResponse(@NotNull Ticket entity){
        TicketResponse response = new TicketResponse();
        response.setId(entity.getId());
        response.setSubject(entity.getSubject());
        response.setPriority(entity.getPriority());
        response.setCreateDateTime(entity.getCreateDateTime());
        response.setUpdateDateTime(entity.getUpdateDateTime());
        return response;
    }

}
