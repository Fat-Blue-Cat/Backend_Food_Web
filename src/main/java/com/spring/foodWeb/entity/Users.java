package com.spring.foodWeb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    @Column(name = "user_name")
    private  String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "create_date")
    private Date create_date;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Roles role = new Roles();

    @OneToMany(mappedBy = "user")
    private Set<Rating> listRating = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<RatingRestaurant> listRatingRestaurant = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Order> orders = new HashSet<>();

}
