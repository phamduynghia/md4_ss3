package com.ra.demo3.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "categories")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "category_id")
    private Long id;
@Column(name = "category_name",length = 100,unique = true)
    private String categoryName;
@Column(name = "category_status")
    private Boolean categoryStatus;
}
