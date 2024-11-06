package com.ra.demo3.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "products_id")
    private Long id;
@Column(name = "products_name",length = 100,unique = true)
    private String productName;
@Column(name = "products_price")
    private Double productPrice;
@Column(name = "products_stock")
private Integer productStock;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
private Category category;
    @Column(name = "products_image")
    private String productImage;
@Column(name = "products_status")
    private Boolean productStatus;
}
