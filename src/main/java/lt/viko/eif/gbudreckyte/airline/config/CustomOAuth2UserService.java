package lt.viko.eif.gbudreckyte.airline.config;

import lombok.RequiredArgsConstructor;
import lt.viko.eif.gbudreckyte.airline.model.User;
import lt.viko.eif.gbudreckyte.airline.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oauthUser = super.loadUser(userRequest);

        String email = oauthUser.getAttribute("email");

        // In some providers email can be null – protect against that
        if (email == null) {
            // No email → no DB lookup, just anonymous authorities
            return new CustomUserPrincipal(
                    oauthUser,
                    Collections.emptySet()
            );
        }

        User user = userRepository.findByEmail(email);

        Set<SimpleGrantedAuthority> authorities;

        if (user != null) {
            // ✅ User already exists → map their roles from DB
            authorities = user.getRoles()
                    .stream()
                    .map(r -> new SimpleGrantedAuthority(r.getName()))  // e.g. ROLE_ADMIN
                    .collect(Collectors.toSet());
        } else {
            // ✅ First-time login → user will be created in OAuthSuccessHandler,
            // but for this request we still want them to have CUSTOMER role
            authorities = Set.of(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
        }

        return new CustomUserPrincipal(oauthUser, authorities);
    }
}
