package com.yammie.yammie.model;

import com.yammie.yammie.model.Order;
import com.yammie.yammie.model.OrderRepository;
import com.yammie.yammie.controller.OrderController;
import com.yammie.yammie.controller.OrderControllerImp;
import junit.framework.TestCase;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;
import java.util.*;


import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static junit.framework.TestCase.assertNotNull;

import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@AutoConfigureMockMvc
@SpringBootTest
@TestPropertySource(locations="classpath:pplication-test.properties")

public class OrderRepositoryTest {



    @Autowired
    private MockMvc mvc;
    @Resource
    private OrderRepository orderRepository;
    @Test
    @Rollback(false)
    /**
     * test the function findAllByDateBetween
     */
    public void getLastDayTest() {
        // current time
        Date dateE=new Date(System.currentTimeMillis());
        //Calculates the time before 24 hours
        Date dateS=new Date(System.currentTimeMillis() - 3600 * 24000);
         // Found between the two dates
        Order order1=new Order("Tel Aviv","pizza",100.99,"shir",new Date(System.currentTimeMillis() - 3600 * 20000));
        //Found between the two dates
        Order order2 =new Order("Bear Shave","hamburger",56.80,"lilach",new Date(System.currentTimeMillis()));
        // Does not found between the two dates
        Order order3=new Order("Rehovot","Sushi",66.4,"koral",new Date(System.currentTimeMillis()-3600*25000));
        orderRepository.saveAll(Lists.newArrayList(order1,order2,order3));
        List<Order> foundEntity = orderRepository.findAllByDateBetween(dateS,dateE);

        TestCase.assertEquals(foundEntity.size(),2);


    }




}
