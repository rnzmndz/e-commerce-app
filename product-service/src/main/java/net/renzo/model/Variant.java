package net.renzo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_variant")
public class Variant extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "price", nullable = false)
    private Price price;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "product_attribute", nullable = false)
    private Set<Attribute> attributes;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
