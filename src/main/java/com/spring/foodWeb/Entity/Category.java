package com.spring.foodWeb.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Category")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name_cate")
    private String nameCate;

    @Column(name = "create_date")
    private Date createDate;

    @OneToMany(mappedBy = "category")
    private Set<Food> listFood;

    @OneToMany(mappedBy = "key.category")
    private Set<MenuRestaurant> listMenuRestaurant;


}
