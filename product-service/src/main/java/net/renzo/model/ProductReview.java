package net.renzo.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_review")
public class ProductReview extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    @Column(name = "comment", nullable = false)
    private String comment;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_detail_id", nullable = false)
    private UserDetail userDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
