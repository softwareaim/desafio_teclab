package com.sooft.alexismolina.domain.model;

import com.sooft.alexismolina.enums.Priority;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tickets")
@SQLDelete(sql = "UPDATE tickets SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class Ticket implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subject", nullable = false,length = 50)
    @NotBlank
    @Size(min = 2, max = 50)
    private String subject;

    @Column(name="priority", nullable = false, length = 6 )
    @Enumerated(value = EnumType.STRING)
    private Priority priority;

    @OneToMany(mappedBy = "ticket", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    private Boolean deleted = Boolean.FALSE;

    public Ticket(String subject, Priority priority) {
        this.subject = subject;
        this.priority = priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;

        Ticket ticket = (Ticket) o;

        if (!getId().equals(ticket.getId())) return false;
        if (!getSubject().equals(ticket.getSubject())) return false;
        if (getPriority() != ticket.getPriority()) return false;
        if (!getComments().equals(ticket.getComments())) return false;
        if (!getCreateDateTime().equals(ticket.getCreateDateTime())) return false;
        return getUpdateDateTime().equals(ticket.getUpdateDateTime());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getSubject().hashCode();
        result = 31 * result + getPriority().hashCode();
        result = 31 * result + getComments().hashCode();
        result = 31 * result + getCreateDateTime().hashCode();
        result = 31 * result + getUpdateDateTime().hashCode();
        return result;
    }
}
