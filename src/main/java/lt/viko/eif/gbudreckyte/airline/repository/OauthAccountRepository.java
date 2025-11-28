package lt.viko.eif.gbudreckyte.airline.repository;

import lt.viko.eif.gbudreckyte.airline.model.OauthAccount;
import lt.viko.eif.gbudreckyte.airline.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OauthAccountRepository extends JpaRepository<OauthAccount, Long> {
    boolean existsByProviderUserId(String providerUserId);
}
