package lt.viko.eif.gbudreckyte.airline.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "oauth_accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OauthAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // --- RELATIONSHIP TO USERS ---
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String provider;          // e.g., "google"
    private String providerUserId;    // e.g., Google ID, GitHub ID
}
