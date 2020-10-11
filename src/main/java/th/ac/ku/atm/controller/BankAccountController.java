package th.ac.ku.atm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.atm.model.BankAccount;
import th.ac.ku.atm.service.BankAccountService;

@Controller
@RequestMapping("/bankaccount")
public class BankAccountController {
    BankAccountService bankAccountService ;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping
    public String GetBankAccountPage(Model model){
        model.addAttribute("AllAccount",bankAccountService.getBankAccounts());
        return "bankaccount";

    }

    @PostMapping
    public String openAccount(@ModelAttribute BankAccount bankAccount, Model model) {
        bankAccountService.openAccount(bankAccount);
        model.addAttribute("bankaccounts",bankAccountService.getBankAccounts());
        return "redirect:bankaccount";
    }
    @GetMapping("/deposit/{id}")
    public String getDepositBankAccountPage(@PathVariable int id, Model model) {
        BankAccount account = bankAccountService.getBankAccount(id);
        model.addAttribute("bankAccount", account);
        return "bankaccount-deposit";
    }

    @GetMapping("/withdraw/{id}")
    public String getWithdrawBankAccountPage(@PathVariable int id, Model model) {
        BankAccount account = bankAccountService.getBankAccount(id);
        model.addAttribute("bankAccount", account);
        return "bankaccount-withdraw";
    }

    @PostMapping("/deposit/{id}")
    public String depositAccount(@PathVariable int id,
                                 @ModelAttribute BankAccount bankAccount,
                                 Model model) {
        bankAccountService.depositBankAccount(bankAccount, bankAccountService.getBankAccount(id).getBalance());
        model.addAttribute("bankaccounts", bankAccountService.getBankAccounts());
        return "redirect:/bankaccount";
    }

    @PostMapping("/withdraw/{id}")
    public String withdrawAccount(@PathVariable int id,
                                  @ModelAttribute BankAccount bankAccount,
                                  Model model) {
        bankAccountService.withDrawBankAccount(bankAccount, bankAccountService.getBankAccount(id).getBalance());
        model.addAttribute("bankaccounts", bankAccountService.getBankAccounts());
        return "redirect:/bankaccount";
    }

    @PostMapping("/delete/{id}")
    public String deleteAccount(@PathVariable int id,
                                @ModelAttribute BankAccount bankAccount,
                                Model model) {
        bankAccountService.deleteBankAccount(bankAccount);
        model.addAttribute("bankaccounts",bankAccountService.getBankAccounts());
        return "redirect:/bankaccount";
    }


}
