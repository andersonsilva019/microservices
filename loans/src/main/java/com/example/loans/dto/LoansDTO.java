package com.example.loans.dto;

import lombok.Data;

@Data
public class LoansDTO {
    private String mobileNumber;

    private String loanNumber;

    private String loanType;

    private int totalLoan;

    private int amountPaid;

    private int outstandingAmount;
}
