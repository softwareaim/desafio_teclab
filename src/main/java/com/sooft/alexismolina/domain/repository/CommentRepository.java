package com.sooft.alexismolina.domain.repository;

import com.sooft.alexismolina.domain.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

//    Page<Comment> findAllByTicket(Ticket ticket, Pageable pageable);

    @Query(value = "SELECT c FROM Comment c JOIN FETCH c.ticket t WHERE t.id = :id",
            countQuery = "SELECT COUNT(c) FROM Comment c JOIN c.ticket t WHERE t.id= :id")
    Page<Comment> findAllByTicketId(@Param("id") Long ticketId, Pageable pageable);


}
