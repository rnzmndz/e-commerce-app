package net.renzo.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_detail")
public class UserDetail extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Review review;
}
