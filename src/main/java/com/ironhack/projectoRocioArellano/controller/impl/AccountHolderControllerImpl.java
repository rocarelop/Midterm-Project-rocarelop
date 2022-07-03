package com.ironhack.projectoRocioArellano.controller.impl;

import com.ironhack.projectoRocioArellano.controller.DTO.AccountHolderDTO;
import com.ironhack.projectoRocioArellano.controller.DTO.SendMoneyDTO;
import com.ironhack.projectoRocioArellano.controller.interfaces.AccountHolderController;
import com.ironhack.projectoRocioArellano.model.accounts.Account;
import com.ironhack.projectoRocioArellano.repository.AccountHolderRepository;
import com.ironhack.projectoRocioArellano.security.CustomUserDetails;
import com.ironhack.projectoRocioArellano.service.interfaces.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
public class AccountHolderControllerImpl implements AccountHolderController {
    @Autowired
    AccountHolderService accountHolderService;
   @GetMapping("/myAccounts")
   @ResponseStatus(HttpStatus.OK)
    public Set<Account> findAllMyAccounts(@AuthenticationPrincipal CustomUserDetails userDetails) {

       return accountHolderService.findAllMyAccounts(userDetails.getUser().getId());
    }

   @PatchMapping("myAccounts/{id}/transfer")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void sendMoney(@PathVariable int id, @AuthenticationPrincipal CustomUserDetails userDetails,
                          @RequestBody @Valid SendMoneyDTO sendMoneyDTO){
       accountHolderService.sendMoney(id, userDetails.getUser().getId(), sendMoneyDTO);
    }


}
