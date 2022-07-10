package com.sooft.alexismolina.controller;

import com.sooft.alexismolina.controller.api.TicketApi;
import com.sooft.alexismolina.domain.model.Comment;
import com.sooft.alexismolina.domain.service.ITicketService;
import com.sooft.alexismolina.domain.dto.PageDTO;
import com.sooft.alexismolina.domain.dto.response.TicketResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
public class TicketController implements TicketApi {

    @Autowired
    private ITicketService ticketService;

    @GetMapping(value = "/{idTicket}/comments")
    public ResponseEntity<PageDTO<Comment>> getCommentsByIdTicket(@PathVariable Long idTicket,
                                                                  @RequestParam(name = "page", defaultValue = "0") int page) {
        return ResponseEntity.ok().body(this.ticketService.listComentsByIdTicket(idTicket, page));
    }

    @PutMapping(value = "/{idTicket}/addComment/{idComment}")
    public ResponseEntity<TicketResponse> addComment(@PathVariable Long idTicket, @PathVariable Long idComment) {
        return ResponseEntity.ok().body(this.ticketService.addComment(idTicket, idComment));

    }

}
