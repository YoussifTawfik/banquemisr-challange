package banquemisr.challenge05.repository;

import banquemisr.challenge05.entity.Todo;
import banquemisr.challenge05.projection.TodoView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long>, JpaSpecificationExecutor<Todo> {

    @Query("Select td from Todo td join td.user u where td.uuid=:todoUuid and u.uuid=:userUuid")
    TodoView findByUuidAndUserUuid(String todoUuid, String userUuid);


    @Modifying
    @Query("DELETE FROM Todo td WHERE td.uuid = :todoUuid AND td.user.uuid = :userUuid")
    void delete(@Param("todoUuid") String todoUuid, @Param("userUuid") String userUuid);


    @Query("Select td from Todo td where td.dueDate between :min and :max")
    List<Todo> fetchTodosInPeriod(@Param("min") LocalDateTime min, @Param("max") LocalDateTime max);
}
