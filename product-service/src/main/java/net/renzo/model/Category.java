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
@Table(name = "category")
public class Category extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "sub_category_id")
    private Category subCategory;

    @OneToMany(mappedBy = "category",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                      CascadeType.REFRESH, CascadeType.DETACH},
            fetch = FetchType.LAZY)
    private Set<Product> products = new HashSet<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }


}
