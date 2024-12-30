package banquemisr.challenge05.specification;

import banquemisr.challenge05.entity.Todo;
import banquemisr.challenge05.entity.User;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class TodoSpecification {

    public static Specification<Todo> filterByUserUuid(String userUuid) {
        return (root, query, criteriaBuilder) -> {
            if (userUuid == null) return criteriaBuilder.conjunction();
            else {
                Join<Todo, User> categoryJoin = root.join("user");
                return criteriaBuilder.equal(categoryJoin.get("uuid"), userUuid);
            }
        };
    }
}
