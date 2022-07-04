package com.ironhack.projectoRocioArellano.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.projectoRocioArellano.controller.DTO.AccountBalanceDTO;
import com.ironhack.projectoRocioArellano.controller.DTO.SendMoneyDTO;
import com.ironhack.projectoRocioArellano.enums.StatusEnum;
import com.ironhack.projectoRocioArellano.model.Address;
import com.ironhack.projectoRocioArellano.model.Money;
import com.ironhack.projectoRocioArellano.model.accounts.*;
import com.ironhack.projectoRocioArellano.model.users.AccountHolder;
import com.ironhack.projectoRocioArellano.model.users.Admin;
import com.ironhack.projectoRocioArellano.model.users.Role;
import com.ironhack.projectoRocioArellano.model.users.User;
import com.ironhack.projectoRocioArellano.repository.AccountHolderRepository;
import com.ironhack.projectoRocioArellano.repository.AccountRepository;
import com.ironhack.projectoRocioArellano.repository.AdminRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.Embedded;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AdminControllerImplTest {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private StudentChecking studentChecking1, studentChecking2;
    private AccountHolder user1, user2;
    private Admin admin1, admin2;
     private Address address1, address2;

     private Role adminRole, accountHolderRole;



    @BeforeEach
    void setUp() {


        address1=new Address("calle mallorca", 7, "sevilla", "spain");
        address2=  new Address("calle salamanca", 28, "sevilla", "spain");

        user1 = new AccountHolder("accountholder", "admin", passwordEncoder.encode("hola"), new Date(1994/12/12), address1, 41014);
        user2 = new AccountHolder("accountholder", "admin", passwordEncoder.encode("hola"), new Date(1996/12/9), address2, 41014);
        admin1= new Admin("admin", "admin", passwordEncoder.encode("hola"));

        accountHolderRepository.saveAll((List.of(user1,user2)));
        adminRepository.save(admin1);

        studentChecking1 =new StudentChecking(new Money(new BigDecimal(5000)), "dd", user1, new Date(2021/01/01), StatusEnum.ACTIVE);
        studentChecking2 =new StudentChecking(new Money(new BigDecimal(3000)), "ee", user1, new Date(2020/03/12), StatusEnum.ACTIVE);

        accountRepository.saveAll(List.of(studentChecking1, studentChecking2));
    }

    @AfterEach
    void tearDown() {
        accountRepository.deleteAll();
        accountHolderRepository.deleteAll();
    }

    @Test
    void findAll_accounts() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic YWRtaW46aG9sYQ==");

        MvcResult mvcResult = mockMvc.perform(get("/accounts").headers(httpHeaders))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains(studentChecking2.getSecretKey()));
        assertTrue(mvcResult.getResponse().getContentAsString().contains(studentChecking1.getSecretKey()));
    }
    @Test
    void findAccountById() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic YWRtaW46aG9sYQ==");

        MvcResult mvcResult = mockMvc.perform(get("/accounts/"+studentChecking1.getId()).headers(httpHeaders))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertFalse(mvcResult.getResponse().getContentAsString().contains(studentChecking2.getSecretKey()));
        assertTrue(mvcResult.getResponse().getContentAsString().contains(studentChecking1.getSecretKey()));
    }

    @Test
    void createSavingsAccount() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic YWRtaW46aG9sYQ==");


        Savings newAccount = new Savings(new Money(new BigDecimal(5000)), user1, new Date(2020/2/2),StatusEnum.ACTIVE);
        String body = objectMapper.writeValueAsString(newAccount);

        MvcResult mvcResult = mockMvc.perform(post("/accounts/savings")
                .headers(httpHeaders)
                .content(body)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains(user1.getName()));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("5000"));


    }

    @Test
    void createCreditCardAccount() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic YWRtaW46aG9sYQ==");


        CreditCard newAccount = new CreditCard(new Money(new BigDecimal(5000)), user1, new Date(2020/2/2),StatusEnum.ACTIVE);
        String body = objectMapper.writeValueAsString(newAccount);

        MvcResult mvcResult = mockMvc.perform(post("/accounts/creditcard")
                        .headers(httpHeaders)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains(user1.getName()));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("5000"));


    }

    @Test
    void createCheckingAccount() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic YWRtaW46aG9sYQ==");


        CreditCard newAccount = new CreditCard(new Money(new BigDecimal(5000)), user1, new Date(2020/2/2),StatusEnum.ACTIVE);
        String body = objectMapper.writeValueAsString(newAccount);

        MvcResult mvcResult = mockMvc.perform(post("/accounts/checking")
                        .headers(httpHeaders)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains(user1.getName()));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("5000"));


    }

    @Test
    void updateBalance()  throws Exception{
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic YWRtaW46aG9sYQ==");

        Money newBalance = new Money(new BigDecimal(3000));
        AccountBalanceDTO accountBalanceDTO = new AccountBalanceDTO(newBalance);

        String body = objectMapper.writeValueAsString(accountBalanceDTO);

        MvcResult mvcResult = mockMvc.perform(patch("/accounts/"+ studentChecking1.getId()+ "/balance")
                        .headers(httpHeaders)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        StudentChecking newStudentChecking1 =new StudentChecking(accountBalanceDTO.getBalance(), "dd", user1, new Date(2021/01/01), StatusEnum.ACTIVE);

        assertEquals(newStudentChecking1.getBalance(), newBalance);

    }

    @Test
    void getBalance()  throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic YWRtaW46aG9sYQ==");

        MvcResult mvcResult = mockMvc.perform(get("/accounts/"+studentChecking1.getId()+"/balance").headers(httpHeaders))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertFalse(mvcResult.getResponse().getContentAsString().contains(studentChecking2.getBalance().getAmount().toString()));
        assertTrue(mvcResult.getResponse().getContentAsString().contains(studentChecking1.getBalance().getAmount().toString()));
    }

    @Test
    void deleteAccount() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic YWRtaW46aG9sYQ==");
        MvcResult mvcResult = mockMvc.perform(delete("/accounts" + studentChecking1.getId()).headers(httpHeaders))
                .andExpect(status()
                .isNoContent())
                .andReturn();
        assertFalse(accountRepository.existsById(studentChecking1.getId()));
    }

}

