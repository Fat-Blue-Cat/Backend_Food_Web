package com.spring.foodWeb.Entity.keys;

import com.spring.foodWeb.Entity.Category;
import com.spring.foodWeb.Entity.Restaurant;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class KeyMenuRestaurant implements Serializable {

    @ManyToOne
    @JoinColumn(name = "cate_id")
    private Category category;

    @JoinColumn(name = "res_id")
    @ManyToOne
    private Restaurant restaurant;
}
