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

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Image> images = new HashSet<>();

    @Transient
    private Image defaultImage;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Variant> variants = new HashSet<>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    }, fetch = FetchType.LAZY)
    @JoinTable(
            name = "product_attribute_relationship",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id")
    )
    private Set<Attribute> attributes = new HashSet<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Review> reviews = new HashSet<>();

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

    public void removeImage(Image image) {
        images.remove(image);
        image.setProduct(null);
    }

    public void addVariant(Variant variant) {
        variants.add(variant);
        variant.setProduct(this);
    }

    public void removeVariant(Variant variant) {
        variants.remove(variant);
        variant.setProduct(null);
    }

    public void addAttribute(Attribute attribute) {
        attributes.add(attribute);
        attribute.getProducts().add(this);
    }

    public void removeAttribute(Attribute attribute) {
        attributes.remove(attribute);
        attribute.getProducts().remove(this);
    }

    public void addReview(Review review) {
        reviews.add(review);
        review.setProduct(this);
    }

    public void removeReview(Review review) {
        reviews.remove(review);
        review.setProduct(null);
    }

    public void setBrand(Brand newBrand) {
        if (this.brand != null) {
            this.brand.getProducts().remove(this);
        }
        this.brand = newBrand;
        if (newBrand != null) {
            newBrand.getProducts().add(this);
        }
    }

    public void addCategory(Category category) {
        category.getProducts().add(this);
    }

    public void removeCategory(Category category) {
        category.getProducts().remove(this);
    }

}
