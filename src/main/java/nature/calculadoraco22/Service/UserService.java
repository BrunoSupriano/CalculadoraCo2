package nature.calculadoraco22.Service;

import nature.calculadoraco22.Dto.UserDto;
import nature.calculadoraco22.Model.User;
import nature.calculadoraco22.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        return userRepository.save(user);
    }

    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}