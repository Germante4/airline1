package lt.viko.eif.gbudreckyte.airline.controller;

import lt.viko.eif.gbudreckyte.airline.model.Role;
import lt.viko.eif.gbudreckyte.airline.repository.RoleRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleRepository repo;

    public RoleController(RoleRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Role> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Role create(@RequestBody Role role) {
        return repo.save(role);
    }
}
