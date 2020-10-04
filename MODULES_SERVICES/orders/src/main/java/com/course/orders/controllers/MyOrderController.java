package com.course.orders.controllers;

import com.course.orders.domain.MyOrder;
import com.course.orders.domain.MyOrderItem;
import com.course.orders.repositories.MyOrderItemRepository;
import com.course.orders.repositories.MyOrderRepository;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
public class MyOrderController {

    @Autowired
    MyOrderRepository myOrderRepository;

    @Autowired
    MyOrderItemRepository myOrderItemRepository;

    @CrossOrigin
    @PostMapping(value = "/myorder")
    public ResponseEntity<MyOrder> createNewOrder(@RequestBody MyOrder myOrderData)
    {
        MyOrder myOrder = myOrderRepository.save(new MyOrder());

        if (myOrder == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Couldn't create a new order");

        return new ResponseEntity<MyOrder>(myOrder, HttpStatus.CREATED);
    }


    @GetMapping(value = "/myorder/{id}")
    public Optional<MyOrder> getMyOrder(@PathVariable Long id)
    {
        Optional<MyOrder> order = myOrderRepository.findById(id);

        if (order == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Couldn't get order");

        return order;
    }

    @PostMapping(value = "/myorder/{id}")
    @CrossOrigin
    @Transactional
    public ResponseEntity<MyOrderItem> addProductToMyOrder(@PathVariable Long id, @RequestBody MyOrderItem myOrderItem)
    {
        MyOrder myOrder = myOrderItemRepository.getOne(id);

        if (myOrder == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Couldn't get order");

        myOrder.addProduct(myOrderItem);

        myOrderRepository.save(myOrder);

        return new ResponseEntity<MyOrderItem>(myOrderItem, HttpStatus.CREATED);
    }

    @CrossOrigin
    @GetMapping(value = "/myorders")
    public List<MyOrder> list()
    {
        List<MyOrder> ordersList = myOrderRepository.findAll();
        return ordersList;
    }

}
