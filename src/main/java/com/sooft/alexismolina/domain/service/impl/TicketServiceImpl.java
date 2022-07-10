package com.sooft.alexismolina.domain.service.impl;

import com.sooft.alexismolina.domain.model.Comment;
import com.sooft.alexismolina.domain.model.Ticket;
import com.sooft.alexismolina.domain.repository.CommentRepository;
import com.sooft.alexismolina.domain.repository.TicketRepository;
import com.sooft.alexismolina.domain.service.ITicketService;
import com.sooft.alexismolina.domain.dto.PageDTO;
import com.sooft.alexismolina.domain.dto.response.TicketResponse;
import com.sooft.alexismolina.exception.NotFoundException;
import com.sooft.alexismolina.mapper.CommentMapper;
import com.sooft.alexismolina.mapper.TicketMapper;
import com.sooft.alexismolina.util.paginator.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements ITicketService {


    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private TicketMapper ticketMapper;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CommentMapper commentMapper;
    @Value("${NotFound.ticket}")
    private String ErrorTicket;
    @Value("${NotFound.comment}")
    private String ErrorComment;

    @Override
    public PageDTO<Comment> listComentsByIdTicket(Long id, int page) {
        this.ticketRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorTicket));
        Page<Comment> comments = this.commentRepository.findAllByTicketId(id, PageRequest.of(page, Url.MAX_PAGE));
        PageDTO<Comment> commentDTO = new PageDTO<>();
        return Url.pagination(commentDTO, comments, page, commentMapper.entitiesPage2Dto(comments), Url.TICKETS_URI + "/" + id + Url.COMMENTS_URI);
    }

    @Override
    public TicketResponse addComment(Long idTicket, Long idComment) {
        Ticket entityTicket = this.ticketRepository.findById(idTicket).orElseThrow(() -> new NotFoundException(ErrorTicket));
        Comment entityComment = this.commentRepository.findById(idComment).orElseThrow(() -> new NotFoundException(ErrorComment));
        entityComment.setTicket(entityTicket);
        this.commentRepository.save(entityComment);
        return this.ticketMapper.entityToResponse(entityTicket);
    }
}
