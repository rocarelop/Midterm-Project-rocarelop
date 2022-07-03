package com.ironhack.projectoRocioArellano.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.projectoRocioArellano.controller.DTO.SendMoneyDTO;
import com.ironhack.projectoRocioArellano.enums.StatusEnum;
import com.ironhack.projectoRocioArellano.model.Address;
import com.ironhack.projectoRocioArellano.model.Money;
import com.ironhack.projectoRocioArellano.model.accounts.Account;
import com.ironhack.projectoRocioArellano.model.accounts.StudentChecking;
import com.ironhack.projectoRocioArellano.model.users.AccountHolder;
import com.ironhack.projectoRocioArellano.model.users.Admin;
import com.ironhack.projectoRocioArellano.model.users.Role;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AccountHolderControllerImplTest {

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

    private Role adminRole, accountholderRole;

    @BeforeEach
    void setUp() {


        address1=new Address("calle mallorca", 7, "sevilla", "spain");
        address2=  new Address("calle salamanca", 28, "sevilla", "spain");

        user1 = new AccountHolder("accountholder", "accountholder", "hola", new Date(1994/12/12), address1, 41014);
        user2 = new AccountHolder("accountholder", "accountholder", "hola", new Date(1996/12/9), address2, 41014);
        admin1= new Admin("admin", "admin", "hola");

        accountHolderRepository.saveAll((List.of(user1,user2)));
        adminRepository.save(admin1);

        studentChecking1 =new StudentChecking(new Money(new BigDecimal(5000)), "dd", user1, new Date(2021/01/01), StatusEnum.ACTIVE);
        studentChecking2 =new StudentChecking(new Money(new BigDecimal(3000)), "ee", user2, new Date(2020/03/12), StatusEnum.ACTIVE);

        accountRepository.saveAll(List.of(studentChecking1, studentChecking2));
    }

    @AfterEach
    void tearDown() {accountRepository.deleteAll(); }

    @Test
    void findAllMyAccounts() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic YWNjb3VudGhvbGRlcjpob2xh");

        MvcResult mvcResult = mockMvc.perform(get("/myAccounts").headers(httpHeaders))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains(studentChecking2.getSecretKey()));
        assertFalse(mvcResult.getResponse().getContentAsString().contains(studentChecking1.getSecretKey()));

    }

    @Test
    void sendMoney() throws Exception{
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic YWNjb3VudGhvbGRlcjpob2xh");

        Money amountTransferencia = new Money(new BigDecimal(100));
        SendMoneyDTO sendMoneyDTO = new SendMoneyDTO(studentChecking2.getPrimaryOwner().getName(), amountTransferencia, studentChecking1.getId(), studentChecking1.getPrimaryOwner().getName());
        String body = objectMapper.writeValueAsString(sendMoneyDTO);

        MvcResult mvcResult = mockMvc.perform(patch("/myAccounts/"+ studentChecking2.getId()+ "/transfer")
                        .headers(httpHeaders)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isCreated())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andReturn();


        Money newStudentChecking2 = new Money(studentChecking2.getBalance().decreaseAmount(amountTransferencia));
        Money newStudentChecking1 = new Money(studentChecking1.getBalance().decreaseAmount(amountTransferencia));

        Optional<Account> optionalAccount1 = accountRepository.findById(studentChecking2.getId());
        Optional<Account> optionalAccount2 = accountRepository.findById(studentChecking1.getId());

        assertEquals(optionalAccount1.get().getBalance().getAmount(), newStudentChecking2.getAmount());
        assertEquals(optionalAccount2.get().getBalance().getAmount(), newStudentChecking1.getAmount());
    }

}