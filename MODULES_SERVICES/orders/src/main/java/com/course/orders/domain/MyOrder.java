package com.course.orders.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class MyOrder {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<MyOrderItem> products;

    private Long total;


//    public MyOrder (Long id) {
//        this.id = id;
//    }

    public MyOrder() { }


    public Long getTotal() {
        return total;
    }

    public void setTotal(Long id) {
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<MyOrderItem> getProducts() {
        return products;
    }


    public void addProduct(MyOrderItem product) {
        this.products.add(product);
    }
}




