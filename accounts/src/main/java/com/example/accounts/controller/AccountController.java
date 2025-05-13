package com.example.accounts.controller;

import com.example.accounts.constants.AccountsConstants;
import com.example.accounts.dto.CustomerDTO;
import com.example.accounts.dto.ResponseDTO;
import com.example.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class AccountController {

    private IAccountsService iAccountsService;

    @PostMapping("/create")
   public ResponseEntity<ResponseDTO> createAccount(@RequestBody CustomerDTO customerDTO){

        iAccountsService.createAccount(customerDTO);

       return ResponseEntity
               .status(HttpStatus.CREATED)
               .body(
                       new ResponseDTO(
                               AccountsConstants.STATUS_201,
                               AccountsConstants.MESSAGE_201
                       )
               );
   }

   @GetMapping("/fetch")
   public ResponseEntity<CustomerDTO> fetchAccountDetails(@RequestParam String mobileNumber){
    CustomerDTO customerDTO = iAccountsService.fetchAccount(mobileNumber);

    return ResponseEntity.status(HttpStatus.OK).body(customerDTO);
   }
}
