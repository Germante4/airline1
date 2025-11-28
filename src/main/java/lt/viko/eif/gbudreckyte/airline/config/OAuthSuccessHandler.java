package lt.viko.eif.gbudreckyte.airline.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lt.viko.eif.gbudreckyte.airline.model.OauthAccount;
import lt.viko.eif.gbudreckyte.airline.model.Role;
import lt.viko.eif.gbudreckyte.airline.model.User;
import lt.viko.eif.gbudreckyte.airline.repository.OauthAccountRepository;
import lt.viko.eif.gbudreckyte.airline.repository.RoleRepository;
import lt.viko.eif.gbudreckyte.airline.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class OAuthSuccessHandler implements AuthenticationSuccessHandler {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final OauthAccountRepository oauthAccountRepository;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {

        OAuth2User oauthUser = (OAuth2User) authentication.getPrincipal();

        String email = oauthUser.getAttribute("email");
        String firstName = oauthUser.getAttribute("given_name");
        String lastName = oauthUser.getAttribute("family_name");
        String providerId = oauthUser.getAttribute("sub");

        if (lastName == null) lastName = "";

        User user = userRepository.findByEmail(email);

        if (user == null) {
            user = new User(firstName, lastName, email);
            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());

            Role defaultRole = roleRepository.findByName("ROLE_CUSTOMER");
            if (defaultRole == null) {
                defaultRole = new Role("ROLE_CUSTOMER");
                roleRepository.save(defaultRole);
            }

            user.getRoles().add(defaultRole);
            userRepository.save(user);
        }
        if (email.equalsIgnoreCase("budreckytegermante@gmail.com")) {
            Role adminRole = roleRepository.findByName("ROLE_ADMIN");
            if (adminRole == null) {
                adminRole = new Role("ROLE_ADMIN");
                roleRepository.save(adminRole);
            }
            user.getRoles().add(adminRole);
        }

        // Store oauth data if needed
        if (!oauthAccountRepository.existsByProviderUserId(providerId)) {
            OauthAccount oa = new OauthAccount();
            oa.setUser(user);
            oa.setProvider("google");
            oa.setProviderUserId(providerId);
            oauthAccountRepository.save(oa);
        }

        // -----------------------
        // 🚀 ROLE-BASED REDIRECT
        // -----------------------
        String redirectUrl = "/customer/dashboard"; // default

        if (user.getRoles().stream().anyMatch(r -> r.getName().equals("ROLE_ADMIN"))) {
            redirectUrl = "/admin/dashboard";
        } else if (user.getRoles().stream().anyMatch(r -> r.getName().equals("ROLE_STAFF"))) {
            redirectUrl = "/staff/dashboard";
        }

        response.sendRedirect(redirectUrl);
    }


}
