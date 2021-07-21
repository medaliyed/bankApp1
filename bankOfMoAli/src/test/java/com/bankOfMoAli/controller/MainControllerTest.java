package com.bankOfMoAli.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.bankOfMoAli.dao.BankAccountDAO;

public class MainControllerTest {

	@MockBean
	BankAccountDAO bankDao;

}
