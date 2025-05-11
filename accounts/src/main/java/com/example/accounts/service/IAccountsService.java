package com.example.accounts.service;

import com.example.accounts.dto.CustomerDTO;

public interface IAccountsService {

    /**
     * Creates a new account for the given customer.
     *
     * @param customerDTO the customer details used to create the account
     */
    void createAccount(CustomerDTO customerDTO);
}
