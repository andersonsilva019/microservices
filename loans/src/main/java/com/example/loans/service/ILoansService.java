package com.example.loans.service;

import com.example.loans.dto.LoansDTO;

public interface ILoansService {

    void createLoan(String mobileNumber);

    LoansDTO fetchLoans(String mobileNumber);

    boolean updateLoans(LoansDTO loansDTO);

    boolean deleteLoan(String mobileNumber);
}
