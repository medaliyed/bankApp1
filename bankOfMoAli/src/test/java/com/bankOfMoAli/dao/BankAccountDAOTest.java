package com.bankOfMoAli.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.bankOfMoAli.entity.BankAccount;
import com.bankOfMoAli.exception.BankTransactionException;

@SpringBootTest
public class BankAccountDAOTest {
	
	@MockBean
	private BankAccountDAO bankDAO;
	
	@Test
	public void createAccountTest() {
		BankAccount bankAcc = new BankAccount(1L,"ali", 200);
		
		bankDAO.createAccount(bankAcc.getId(), bankAcc.getFullName(), bankAcc.getBalance());
		
		assertThat(bankDAO.findById(bankAcc.getId())).isEqualTo(true);
	}
	
	@Test
	public void listBankAccountInfoTest() {
		BankAccount bankAcc = new BankAccount(1L,"ali", 200);
		BankAccount bankAcc2 = new BankAccount(2L,"ali2", 2000);
		bankDAO.createAccount(bankAcc.getId(), bankAcc.getFullName(), bankAcc.getBalance());
		bankDAO.createAccount(bankAcc2.getId(), bankAcc2.getFullName(), bankAcc2.getBalance());
		
		assertThat(bankDAO.listBankAccountInfo()).isEqualTo(2);
	}
	
	@Test
	public void addAmountTest() throws BankTransactionException {
		BankAccount bankAcc = new BankAccount(1L,"ali", 200);
		
		bankDAO.createAccount(bankAcc.getId(), bankAcc.getFullName(), bankAcc.getBalance());
		bankDAO.addAmount(bankAcc.getId(), 300);
		assertThat(bankAcc.getBalance()).isEqualTo(500);
	}
}
