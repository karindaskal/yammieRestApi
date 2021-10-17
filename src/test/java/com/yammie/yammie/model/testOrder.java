package com.yammie.yammie.model;

import com.yammie.yammie.model.Order;
import com.yammie.yammie.model.OrderRepository;
import com.yammie.yammie.controller.OrderController;
import com.yammie.yammie.controller.OrderControllerImp;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import javax.annotation.Resource;
import java.util.*;


import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static junit.framework.TestCase.assertNotNull;

import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@AutoConfigureMockMvc
@SpringBootTest
public class testOrder {



    @Autowired
    private MockMvc mvc;
    @Resource
    private OrderRepository orderRepository;
    @Test
    public void getLastDayTest() {
        Date dateE=new Date(System.currentTimeMillis());

        Date dateS=new Date(System.currentTimeMillis() - 3600 * 24000);
        Order genericEntity = orderRepository
                .save(new Order("tel aviv","pizza",90.9,"shir",new Date(System.currentTimeMillis() - 3600 * 22000)));
        List<Order> foundEntity = orderRepository.findAllByDateBetween(dateS,dateE);

        TestCase.assertEquals(foundEntity.size(),1);


    }




}
