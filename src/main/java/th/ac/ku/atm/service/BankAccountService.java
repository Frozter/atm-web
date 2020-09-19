package th.ac.ku.atm.service;

import org.springframework.stereotype.Service;
import th.ac.ku.atm.model.BankAccount;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class BankAccountService {
    private List<BankAccount> bankAccounts;
    @PostConstruct
    public void BankAccountService(){
        bankAccounts = new ArrayList<>();
    }
    public void CreateAccount(BankAccount bankAccount){
        bankAccounts.add(bankAccount);
    }

    public ArrayList<BankAccount> getBankAccounts() {
        return new ArrayList<>(this.bankAccounts);
    }
}
