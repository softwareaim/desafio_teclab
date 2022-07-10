package com.sooft.alexismolina.controller.api;

import com.sooft.alexismolina.domain.dto.PageDTO;
import com.sooft.alexismolina.domain.dto.response.TicketResponse;
import com.sooft.alexismolina.domain.model.Comment;
import com.sooft.alexismolina.exception.ApiErrorDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface TicketApi {

    @Operation(summary = "get all  Commets", description = "get comments ", responses = {
            @ApiResponse(responseCode = "200", description = "get comments ",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PageDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Invalid token or token expired",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorDTO.class))}),
            @ApiResponse(responseCode = "403", description = "Invalid Role",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content)
    })
    ResponseEntity<PageDTO<Comment>> getCommentsByIdTicket(@PathVariable Long idTicket,
                                                           @RequestParam(name = "page", defaultValue = "0") int page);

    @Operation(summary = "add a comment", description = "update a ticket completely", responses = {
            @ApiResponse(responseCode = "200", description = "update comment "),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Invalid token or token expired",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorDTO.class))}),
            @ApiResponse(responseCode = "403", description = "Invalid Role",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content)})
    ResponseEntity<TicketResponse> addComment(@PathVariable Long idTicket, @PathVariable Long idComment);

}
