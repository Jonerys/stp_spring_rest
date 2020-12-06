package jonerys.test.springcrud.service;

import jonerys.test.springcrud.model.User;
import jonerys.test.springcrud.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByLogin(String login) { return userRepository.findByLogin(login); }

    public void deleteByLogin(String login) { userRepository.deleteByLogin(login); }

    public User save(User user) { return userRepository.save(user); }
}
