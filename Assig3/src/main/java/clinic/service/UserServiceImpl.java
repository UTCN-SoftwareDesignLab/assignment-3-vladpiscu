package clinic.service;

import clinic.entity.User;
import clinic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username,password );
    }

    @Override
    public User save(User user) {
        user.setPassword(user.getPassword());
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        user.setPassword(user.getPassword());
        return userRepository.save(user);
    }

    @Override
    public void remove(User user) {
        userRepository.delete(user);
    }

    @Override
    public void remove(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public void removeAll() {
        userRepository.deleteAll();
    }
}
