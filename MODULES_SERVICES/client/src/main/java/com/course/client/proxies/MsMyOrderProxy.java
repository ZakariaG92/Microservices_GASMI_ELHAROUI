package com.course.client.proxies;

import com.course.client.beans.MyOrderBean;
import com.course.client.beans.MyOrderItemBean;
import com.course.client.beans.ProductBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "ms-order", url = "localhost:8095")
public interface MsMyOrderProxy {

    @PostMapping(value = "/myorder")
    public ResponseEntity<MyOrderBean> createNewOrder(@RequestBody MyOrderBean myOrderData);

    @GetMapping(value = "/myorder/{id}")
    public Optional<MyOrderBean> getMyOrder(@PathVariable Long id);


    @PostMapping(value = "/myorder/{id}")
    public ResponseEntity<MyOrderItemBean> addProductToMyOrder(@PathVariable Long id, @RequestBody MyOrderItemBean myOrderItem);

    @GetMapping(value = "/myorders")
    public List<MyOrderBean> list();




}
