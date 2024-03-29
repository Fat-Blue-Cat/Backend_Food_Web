package com.spring.foodWeb.entity;

import com.spring.foodWeb.entity.keys.KeyMenuRestaurant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Menu_restaurant")
public class MenuRestaurant {
    @EmbeddedId
    private KeyMenuRestaurant key;

    @Column(name = "create_date")
    private Date createDate;


}
