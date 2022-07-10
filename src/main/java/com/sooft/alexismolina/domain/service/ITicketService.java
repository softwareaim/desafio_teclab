package com.sooft.alexismolina.domain.service;

import com.sooft.alexismolina.domain.model.Comment;
import com.sooft.alexismolina.domain.dto.PageDTO;
import com.sooft.alexismolina.domain.dto.response.TicketResponse;
import org.springframework.transaction.annotation.Transactional;

public interface ITicketService {


    @Transactional(readOnly = true)
    PageDTO<Comment> listComentsByIdTicket(Long id, int page);

    @Transactional
    TicketResponse addComment(Long idTicket, Long idComment);
}
