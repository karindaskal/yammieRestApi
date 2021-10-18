package com.yammie.yammie.controller;

import com.yammie.yammie.model.Order;
import com.yammie.yammie.model.OrderRepository;
import junit.framework.TestCase;
import org.assertj.core.util.Lists;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
@TestPropertySource(locations="classpath:pplication-test.properties")
public class OrderControllerTest {
    @Autowired
    private MockMvc mvc;
    @Resource
    private OrderRepository orderRepository;
    @Test
    /**
     *  Test save new order
     */
    public void postTest() throws Exception {

       RequestBuilder request = MockMvcRequestBuilders
                .post("/order")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"noy\",\"price\":10,\"adress\":100,\"description\":\"soup\"}"	)
                .contentType(MediaType.APPLICATION_JSON);
        System.out.println(request);
        System.out.println(mvc);
        MvcResult result = mvc.perform(request)
                .andExpect(status().isCreated())
                .andReturn();


    }

    @Test
    /**
     * test to get all orders from the last day
     */
    public void getTest() throws Exception {
         //Occurs on the last day
        Order order1=new Order("Tel Aviv","pizza",100.99,"shir",new Date(System.currentTimeMillis() - 3600 * 20000));
      //  Occurs on the last day
        Order order2 =new Order("Bear Shave","hamburger",56.80,"lilach",new Date(System.currentTimeMillis()));
        //Does not Occurs on the last day
        Order order3=new Order("Rehovot","Sushi",66.4,"koral",new Date(System.currentTimeMillis()-3600*25000));

        orderRepository.saveAll(Lists.newArrayList(order1,order2,order3));

        mvc.perform(MockMvcRequestBuilders
                .get("/order")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("[0].name",is("shir")));

    }



}
