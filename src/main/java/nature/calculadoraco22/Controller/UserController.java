package nature.calculadoraco22.Controller;

import nature.calculadoraco22.Dto.UserDto;
import nature.calculadoraco22.Model.User;
import nature.calculadoraco22.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/ajuda")
    public ResponseEntity<Map<String, Object>> ajuda() {
        Map<String, Object> info = new HashMap<>();
    info.put("estudantes", List.of("Bruno", "Gustavo", "Julia"));
        info.put("projeto", "CalculadoraCO2");
        info.put("tema", "Mudanças Climáticas");
        return ResponseEntity.ok(info);
    }

    @PostMapping("/api/users")
    public User addUser(@RequestBody UserDto userDto) {
        return userService.addUser(userDto);
    }

    @GetMapping("/api/users/{id}")
    public Optional<User> getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @DeleteMapping("/api/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
