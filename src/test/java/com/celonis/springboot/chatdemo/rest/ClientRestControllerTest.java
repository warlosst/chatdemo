package com.celonis.springboot.chatdemo.rest;


import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup("/clientData.xml")
@DisplayName("Write Client Rest Controller Tests")
class ClientRestControllerTest {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype());
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    @InjectMocks
    private ClientRestController clientRestController;

    @Autowired
    private WebApplicationContext context;
    @BeforeEach
    void setUp() throws Exception{

        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    @DisplayName("Find all users test")
    @ExpectedDatabase(value="/clientData.xml",assertionMode=DatabaseAssertionMode.NON_STRICT)
    void testFindAllClients() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/clients"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(content().string("["
                        + "{\"id\":1,\"username\":\"defrim\",\"email\":\"dm@hotmail.com\"}"
                        +"]"
                ));
    }

    @Test
    @DisplayName("Find user by id - found")
    @ExpectedDatabase(value="/clientData.xml",assertionMode=DatabaseAssertionMode.NON_STRICT)
    void testFindById() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/clients/{id}",1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(content().string( "{\"id\":1," +
                        "\"username\":\"defrim\",\"email\":\"dm@hotmail.com\"}"
                ));
    }

    @Test
    @DisplayName("Find user by id - not found")
    @ExpectedDatabase(value="/clientData.xml",assertionMode=DatabaseAssertionMode.NON_STRICT)
    void testFindByIdWhenClientNotFound() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/clients/{id}",3))
                .andExpect(status().isNotFound());
    }

}