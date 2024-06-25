package nature.calculadoraco22;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import nature.calculadoraco22.Dto.UserDto;
import nature.calculadoraco22.Model.User;
import nature.calculadoraco22.Repositories.UserRepository;
import nature.calculadoraco22.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddUser() {
        UserDto userDto = new UserDto();
        userDto.setUsername("testuser");

        User user = new User();
        user.setUsername("testuser");

        when(userRepository.save(any(User.class))).thenReturn(user);

        User createdUser = userService.addUser(userDto);

        assertNotNull(createdUser);
        assertEquals("testuser", createdUser.getUsername());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testGetUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Optional<User> foundUser = userService.getUser(1L);

        assertTrue(foundUser.isPresent());
        assertEquals("testuser", foundUser.get().getUsername());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteUser() {
        Long userId = 1L;

        doNothing().when(userRepository).deleteById(userId);

        userService.deleteUser(userId);

        verify(userRepository, times(1)).deleteById(userId);
    }
}
