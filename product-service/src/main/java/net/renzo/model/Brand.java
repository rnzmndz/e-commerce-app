package net.renzo.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "brand")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "logo")
    private String logo;

    @OneToOne(mappedBy = "brand", cascade = {CascadeType.PERSIST, CascadeType.MERGE
            , CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    private Product product;
}
