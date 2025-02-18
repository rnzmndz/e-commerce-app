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
// TODO Review each field and improve
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
    private Set<Image> images;

    @Transient
    private Image defaultImage;

    @OneToMany(mappedBy = "product",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<Variant> variants;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    }, fetch = FetchType.LAZY)
    @JoinTable(
        name = "product_attribute",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "attribute_id")
    )
    private Set<Attribute> attributes;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Review> reviews;

    @PostLoad
    private void setDefaultImage() {
        if (images != null) {
            for (Image image : images) {
                if (image.getIsPrimary()) {
                    this.defaultImage = image;
                    break;
                }
            }
        }
    }

    public void addImage(Image image) {
        images.add(image);
        image.setProduct(this);
    }

    private void removeImage(Image image) {
        images.remove(image);
        image.setProduct(null);
    }

    public void addVariant(Variant variant) {
        variants.add(variant);
        variant.setProduct(this);
    }

    private void removeVariant(Variant variant) {
        variants.remove(variant);
        variant.setProduct(null);
    }

    public void addAttribute(Attribute attribute) {
        attributes.add(attribute);
        attribute.getProducts().add(this);
    }

    private void removeAttribute(Attribute attribute) {
        attributes.remove(attribute);
        attribute.getProducts().remove(this);
    }

    public void addReview(Review review) {
        reviews.add(review);
        review.setProduct(this);
    }

    private void removeReview(Review review) {
        reviews.remove(review);
        review.setProduct(null);
    }

}
