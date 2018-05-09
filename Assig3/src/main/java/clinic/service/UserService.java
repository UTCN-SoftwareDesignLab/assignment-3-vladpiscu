package clinic.service;

import clinic.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findByUsernameAndPassword(String username, String password);
    User save(User user);
    User update(User user);
    void remove(User user);
    void remove(int id);
    void removeAll();
}
