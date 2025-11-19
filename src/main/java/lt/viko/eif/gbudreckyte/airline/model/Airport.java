package lt.viko.eif.gbudreckyte.airline.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "airport")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String name;
    private String city;
    private String country;
    private Double latitude;
    private Double longitude;
}
