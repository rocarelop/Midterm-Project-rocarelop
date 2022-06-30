package com.ironhack.projectoRocioArellano.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.projectoRocioArellano.enums.StatusEnum;
import com.ironhack.projectoRocioArellano.model.Address;
import com.ironhack.projectoRocioArellano.model.Money;
import com.ironhack.projectoRocioArellano.model.accounts.Account;
import com.ironhack.projectoRocioArellano.model.accounts.Checking;
import com.ironhack.projectoRocioArellano.model.accounts.StudentChecking;
import com.ironhack.projectoRocioArellano.model.users.AccountHolder;
import com.ironhack.projectoRocioArellano.repository.AccountRepository;
import com.ironhack.projectoRocioArellano.repository.AdminRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/*
@SpringBootTest
@AutoConfigureMockMvc
class AdminControllerImplTest {
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private StudentChecking studentChecking1, studentChecking2;
    private AccountHolder accountHolder1, accountHolder2;

    @BeforeEach
    void setUp() {

        //String name, String username, String password, Set<Role> roles, Date dateOfBirth, Address primaryAddress, Integer mailingAddress, Set<Account> primaryAccounts, Set<Account> secondaryAccounts
        accountHolder1= "Rocio Arellano", "rocarelop", "dddd",
        accountHolder2=
        studentChecking1 =new StudentChecking(new Money(new BigDecimal(5000)), "dddd", accountHolder1, new Date(2021/01/01), StatusEnum.ACTIVE);
        studentChecking2 =new StudentChecking(new Money(new BigDecimal(5000)), "dddd", accountHolder1, , StatusEnum.ACTIVE);

        accountRepository.saveAll(List.of(studentChecking1, studentChecking2));
    }

    @AfterEach
    void tearDown() {
        accountRepository.deleteAll();
    }

    @Test
    void findAll_accounts() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/accounts"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("2000"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("1000"));
    }

    @Test
    void findAccountById() {
    }

    @Test
    void store() {
    }

    @Test
    void updateBalance() {
    }
}
*/
