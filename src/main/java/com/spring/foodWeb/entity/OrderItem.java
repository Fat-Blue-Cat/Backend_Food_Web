package com.spring.foodWeb.entity;

import com.spring.foodWeb.entity.keys.KeyOrderItem;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
@Table(name = "Order_item")
public class OrderItem {

    @EmbeddedId
    private KeyOrderItem key;

    @Column(name = "create_date")
    private Date createDate;


}
