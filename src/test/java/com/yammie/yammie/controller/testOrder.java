package com.yammie.yammie.controller;

import com.yammie.yammie.model.Order;
import com.yammie.yammie.model.OrderRepository;
import junit.framework.TestCase;
import org.assertj.core.util.Lists;
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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
public class testOrder {



    @Autowired
    private MockMvc mvc;
    @Resource
    private OrderRepository orderRepository;


    @Test
    public void postTest() throws Exception {
        Order emp = new Order("sdsa","asd",90.9,"sada",new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime());

       RequestBuilder request = MockMvcRequestBuilders
                .post("/order")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"name\":\"Book\",\"price\":10,\"adress\":100}"	)
                .contentType(MediaType.APPLICATION_JSON);
        System.out.println(request);
        System.out.println(mvc);
        MvcResult result = mvc.perform(request)
                .andExpect(status().isCreated())
                .andReturn();
        


    }

    @Test
    public void getTest() throws Exception {

                Order order1=new Order("Tel Aviv","pizza",100.99,"shir",new Date(System.currentTimeMillis() - 3600 * 20000));
               Order order2 =new Order("Bear Shave","hamburger",56.80,"lilach",new Date(System.currentTimeMillis()));
                Order order3=new Order("Rehovot","Sushi",66.4,"koral",new Date(System.currentTimeMillis()-3600*25000));
                orderRepository.saveAll(Lists.newArrayList(order1,order2,order3));


        mvc.perform(MockMvcRequestBuilders
                .get("/order")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("[0].name",is("shir")));

    }


}
