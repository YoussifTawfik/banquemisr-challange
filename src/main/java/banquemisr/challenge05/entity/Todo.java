package banquemisr.challenge05.entity;

import banquemisr.challenge05.enums.TodoPriority;
import banquemisr.challenge05.enums.TodoStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "TODOS_TBL")
@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Todo extends BaseEntity {

    @Column(name = "UUID", nullable = false)
    private String uuid;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    private TodoStatus status = TodoStatus.TODO;

    @Enumerated(EnumType.STRING)
    @Column(name = "PRIORITY")
    private TodoPriority priority;

    @Column(name = "DUE_DATE")
    private LocalDateTime dueDate;

    @ManyToOne
    @JoinColumn(name = "FK_USER_ID")
    private User user;
}
