package com.bankOfMoAli.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bankOfMoAli.dao.BankAccountDAO;
import com.bankOfMoAli.entity.BankAccount;
import com.bankOfMoAli.exception.BankTransactionException;
import com.bankOfMoAli.fom.BankAccountInfo;
import com.bankOfMoAli.fom.SendMoneyForm;

@Controller
public class MainController {

	@Autowired
    private BankAccountDAO bankAccountDAO;
 
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showBankAccounts(Model model) {
    	
        List<BankAccountInfo> list = bankAccountDAO.listBankAccountInfo();
 
        model.addAttribute("accountInfos", list);
 
        return "accountsPage";
    }
 
    @RequestMapping(value = "/sendMoney", method = RequestMethod.GET)
    public String viewSendMoneyPage(Model model) {
 
        SendMoneyForm form = new SendMoneyForm();
 
        model.addAttribute("sendMoneyForm", form);
 
        return "sendMoneyPage";
    }
 
  
    @RequestMapping(value = "/sendMoney", method = RequestMethod.POST)
    public String processSendMoney(Model model, SendMoneyForm sendMoneyForm) {
 
        System.out.println("Send Money: " + sendMoneyForm.getAmount());
 
        try {
            bankAccountDAO.sendMoney(sendMoneyForm.getFromAccountId(), //
                    sendMoneyForm.getToAccountId(), //
                    sendMoneyForm.getAmount());
        } catch (BankTransactionException e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "/sendMoneyPage";
        }
        return "redirect:/";
    }
    
    @RequestMapping(value= "/create", method = RequestMethod.GET)
    public String create(Model model) {
    	//bankAccountDAO.save(bankAccount);
    	
    	BankAccountInfo bankAccount = new BankAccountInfo();
//		model.addAttribute(bankAccount);
    	model.addAttribute("bankAccountForm", bankAccount);
        System.out.println("create");
        return "createPage";
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String processCreateAccount(Model model, BankAccountInfo bankAccountInfo) {
 
        System.out.println("Create Account: " + bankAccountInfo.getId());
 
        try {
            bankAccountDAO.createAccount(bankAccountInfo.getId(), //
            		bankAccountInfo.getFullName(), //
            		bankAccountInfo.getBalance());
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "createPage";
        }
        return "redirect:/";
    }
}
