package com.example.loans.service.impl;

import com.example.loans.constants.LoansConstants;
import com.example.loans.dto.LoansDTO;
import com.example.loans.entity.Loans;
import com.example.loans.exception.ResourceNotFoundException;
import com.example.loans.mappers.LoansMapper;
import com.example.loans.repository.LoansRepository;
import com.example.loans.service.ILoansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class LoansServiceImpl implements ILoansService {

    @Autowired
    private LoansRepository loansRepository;

    @Override
    public void createLoan(String mobileNumber) {

        Optional<Loans> loans = loansRepository.findByMobileNumber(mobileNumber);

        loansRepository.save(createNewLoan(mobileNumber));
    }

    private Loans createNewLoan(String mobileNumber){
        Loans newLoan = new Loans();

        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);

        return newLoan;
    }

    @Override
    public LoansDTO fetchLoans(String mobileNumber) {

        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loans", "mobileNumber", mobileNumber)
        );

        return LoansMapper.mapToLoansDto(loans, new LoansDTO());
    }

    @Override
    public boolean updateLoans(LoansDTO loansDTO) {
        Loans loans = loansRepository.findByLoanNumber(loansDTO.getLoanNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Loans", "loanNumber", loansDTO.getLoanNumber())
        );

        loansRepository.save(LoansMapper.mapDtoToLoans(loansDTO, loans));
        return true;
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {

        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loans", "mobileNumber", mobileNumber)
        );

        loansRepository.deleteById(loans.getLoanId());

        return true;
    }
}
