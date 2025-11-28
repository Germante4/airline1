package lt.viko.eif.gbudreckyte.airline.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lt.viko.eif.gbudreckyte.airline.model.Role;
import lt.viko.eif.gbudreckyte.airline.repository.RoleRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final RoleRepository roleRepository;

    @PostConstruct
    public void init() {
        createRoleIfMissing("ROLE_ADMIN");
        createRoleIfMissing("ROLE_STAFF");
        createRoleIfMissing("ROLE_CUSTOMER");
    }

    private void createRoleIfMissing(String name) {
        if (roleRepository.findByName(name) == null) {
            roleRepository.save(new Role(name));
        }
    }
}
