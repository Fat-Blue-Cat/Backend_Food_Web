package com.spring.foodWeb.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Users1")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    @Column(name = "user_name")
    private  String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "create_date")
    private Date create_date;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private  Role role;

    @OneToMany(mappedBy = "user")
    private Set<Rating> listRating;

    @OneToMany(mappedBy = "user")
    private Set<RatingRestaurant> listRatingRestaurant;

    @OneToMany(mappedBy = "user")
    private Set<Order> orders;

}
