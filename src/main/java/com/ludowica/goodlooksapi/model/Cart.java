package com.ludowica.goodlooksapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("cart")
    private Set<CartProduct> cartProducts;

    public Cart() {
    }

    public Cart(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Set<CartProduct> getCartProduct() {
        return cartProduct;
    }

    public void setCartProduct(Set<CartProduct> cartProduct) {
        this.cartProduct = cartProduct;
    }
}
