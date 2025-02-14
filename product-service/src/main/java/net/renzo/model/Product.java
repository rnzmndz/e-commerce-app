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
@Table(name = "product")
public class Product extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "sku", nullable = false, unique = true)
    private String sku;

    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Category category;

    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Brand brand;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ProductImage> images;

    @Transient
    private ProductImage defaultImage;

    @OneToMany(mappedBy = "product",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<ProductVariant> variants;

    @OneToMany(mappedBy = "product",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<ProductAttribute> attributes;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ProductReview> reviews;

    @PostLoad
    private void setDefaultImage() {
        if (images != null) {
            for (ProductImage image : images) {
                if (image.getIsPrimary()) {
                    this.defaultImage = image;
                    break;
                }
            }
        }
    }


}
