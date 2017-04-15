package pt.ulisboa.tecnico.softeng.bank.domain;

public class WithdrawalAccount extends WithdrawalAccount_Base {
    
    public WithdrawalAccount(Bank bank) {
        setIBAN(bank.getCode() + ++Account.counter);
        setBalance(0);
        setBank(bank);
    }

    public WithdrawalAccount(Bank bank, int amount) {
        setIBAN(bank.getCode() + ++Account.counter);
        setBalance(amount);
        setBank(bank);
    }

}
