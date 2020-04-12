package com.dy.spring_bootweb;


import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.regex.Matcher;

/**
 * 以下代码演示MockMVC
 *
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootWebApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void init(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void queryRestGet() throws Exception {

        // 此处路径 uri 不需要携带端口号、context path 等， 直接使用context path后的请求路径即可
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.get("/rest").contentType(MediaType.APPLICATION_JSON_UTF8));

        String contentAsString = perform.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();

        System.out.println(contentAsString);

    }

    @Test
    public void queryUser() throws Exception{

        // 发送post请求，使用param方法指定参数
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/rest/user/detail")
                                                                            .param("username", "jack")
                                                                            .contentType(MediaType.APPLICATION_JSON_UTF8);


        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                // 执行一些方法，例如打印响应日志
                .andDo(MockMvcResultHandlers.print())
                // 对结果进行一些断言
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                // 获取响应结果
                .andReturn();

        Assert.assertTrue("json正常", mvcResult.getResponse().getContentAsString().startsWith("{"));
    }

}
