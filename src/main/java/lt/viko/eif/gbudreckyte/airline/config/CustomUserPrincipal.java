package lt.viko.eif.gbudreckyte.airline.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

public class CustomUserPrincipal implements OAuth2User {

    private final OAuth2User oauthUser;
    private final Collection<? extends GrantedAuthority> authorities;

    public CustomUserPrincipal(OAuth2User oauthUser,
                               Collection<? extends GrantedAuthority> authorities) {
        this.oauthUser = oauthUser;
        this.authorities = authorities;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oauthUser.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getName() {
        return oauthUser.getName();
    }
}
