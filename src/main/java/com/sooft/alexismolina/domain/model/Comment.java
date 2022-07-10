package com.sooft.alexismolina.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "body", nullable = false, length = 200)
    @NotBlank
    @Size(min = 2, max = 200)
    private String body;

    @ManyToOne(fetch = FetchType.EAGER)
    private Ticket ticket;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    public Comment(String body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;

        Comment comment = (Comment) o;

        if (!getId().equals(comment.getId())) return false;
        if (!getBody().equals(comment.getBody())) return false;
        if (!getTicket().equals(comment.getTicket())) return false;
        if (!getCreateDateTime().equals(comment.getCreateDateTime())) return false;
        return getUpdateDateTime().equals(comment.getUpdateDateTime());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getBody().hashCode();
        result = 31 * result + getTicket().hashCode();
        result = 31 * result + getCreateDateTime().hashCode();
        result = 31 * result + getUpdateDateTime().hashCode();
        return result;
    }
}
