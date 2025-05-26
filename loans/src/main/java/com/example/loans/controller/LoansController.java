package com.example.loans.controller;

import com.example.loans.constants.LoansConstants;
import com.example.loans.dto.LoansDTO;
import com.example.loans.dto.ResponseDTO;
import com.example.loans.service.ILoansService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class LoansController {

    @Autowired
    private ILoansService iLoansService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createLoan(@RequestParam(name = "mobileNumber") String mobileNumber){

        iLoansService.createLoan(mobileNumber);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(LoansConstants.STATUS_201, LoansConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<LoansDTO> fetchLoansDetails(@RequestParam(name = "mobileNumber") String mobileNumber){

        LoansDTO loansDTO = iLoansService.fetchLoans(mobileNumber);

        return ResponseEntity.status(HttpStatus.OK).body(loansDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateLoans(@RequestBody LoansDTO loansDto){

        boolean isUpdated = iLoansService.updateLoans(loansDto);

        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_UPDATE));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteLoan(@RequestParam(name = "mobileNumber") String mobileNumber){

        boolean isDeleted = iLoansService.deleteLoan(mobileNumber);

        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_DELETE));
        }
    }
}
