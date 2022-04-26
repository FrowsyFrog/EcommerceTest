package com.upc.ecommerce.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "order_details")
@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    @Column(name="quantity")
    private Integer quantity;
    @Column(name="price")
    private Double price;
    @Column(name="tax")
    private Double tax;
    @Column(name="upc")
    private String upc;
    @Column(name="total_amount")
    private Double totalAmount;

    @ManyToOne(cascade = CascadeType.ALL)
    private Order order;
}
