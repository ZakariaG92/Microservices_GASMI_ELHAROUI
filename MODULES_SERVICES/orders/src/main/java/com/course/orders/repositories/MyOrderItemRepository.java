package com.course.orders.repositories;

import com.course.orders.domain.MyOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyOrderItemRepository extends JpaRepository <MyOrder, Long>{
}
