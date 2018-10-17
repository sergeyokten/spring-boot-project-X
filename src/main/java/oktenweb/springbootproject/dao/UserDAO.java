package oktenweb.springbootproject.dao;

import oktenweb.springbootproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
