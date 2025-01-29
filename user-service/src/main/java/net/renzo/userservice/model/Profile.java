package net.renzo.userservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bio")
    private String bio;

    @Column(name = "picture_url")
    private String profilePictureUrl;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserDetail user;
}
