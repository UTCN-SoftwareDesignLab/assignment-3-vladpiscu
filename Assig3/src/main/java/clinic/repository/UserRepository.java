package clinic.repository;

import clinic.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsernameAndPassword(String username, String password);
    List<User> findAllByRole(String role);
    void deleteById(int id);
}
