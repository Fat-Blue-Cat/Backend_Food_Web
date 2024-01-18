package com.spring.foodWeb.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Food")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "image")
    private  String image;

    @Column(name="time_ship")
    private String timeShip;

    @Column(name = "is_free_ship")
    private Boolean isFreeShip;

    @Column(name = "price")
    private Double price;

    @OneToMany(mappedBy = "food")
    private Set<Rating> listRating;

    @ManyToOne
    @JoinColumn(name = "cate_id")
    private Category category;

    @OneToMany(mappedBy = "key.food")
    private Set<OrderItem> listOrderItem;
}
