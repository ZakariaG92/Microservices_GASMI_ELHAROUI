package com.course.client.beans;

import java.util.List;

public class MyOrderBean {



    private Long id;

    private List<MyOrderItemBean> products;

    private Long total;


    public MyOrderBean(Long id) {
        this.id = id;
    }

    public MyOrderBean() {
    }

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

    public List<MyOrderItemBean> getProducts() {
        return products;
    }

    public void addProduct(MyOrderItemBean product) {
        this.products.add(product);
    }
}
