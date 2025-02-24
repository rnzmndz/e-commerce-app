package net.renzo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_variant")
public class Variant extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "price_id", nullable = false)
    private Price price;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @OneToMany(mappedBy = "variant", cascade = CascadeType.ALL)
    private Set<Attribute> attributes = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}