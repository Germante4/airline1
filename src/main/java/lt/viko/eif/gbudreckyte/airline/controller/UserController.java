package lt.viko.eif.gbudreckyte.airline.controller;

import lt.viko.eif.gbudreckyte.airline.model.Role;
import lt.viko.eif.gbudreckyte.airline.model.User;
import lt.viko.eif.gbudreckyte.airline.repository.RoleRepository;
import lt.viko.eif.gbudreckyte.airline.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserController(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @GetMapping
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return userRepository.save(user);
    }

    // ★ ADD ROLE TO USER
    @PostMapping("/{id}/add-role")
    public User addRole(
            @PathVariable Long id,
            @RequestParam String roleName
    ) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Role role = roleRepository.findByName(roleName);
        if (role == null)
            throw new RuntimeException("Role not found: " + roleName);

        user.getRoles().add(role);
        return userRepository.save(user);
    }
}
