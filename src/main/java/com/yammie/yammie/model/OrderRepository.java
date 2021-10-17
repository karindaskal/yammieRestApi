package com.yammie.yammie.model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findAllByDateBetween(
            Date publicationTimeStart,
            Date publidaationTimeEnd);

}
