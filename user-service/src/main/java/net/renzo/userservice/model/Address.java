package net.renzo.userservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "address")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "user")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zipCode")
    private String zipCode;

    @Column(name = "country")
    private String country;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserDetail user;

    public String getCompleteAddress(){
        return String.format("%s, %s, %s, %s, %s", street, city, state, zipCode, country);
    }
}
