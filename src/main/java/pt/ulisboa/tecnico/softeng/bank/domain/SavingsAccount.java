package pt.ulisboa.tecnico.softeng.bank.domain;

public class SavingsAccount extends SavingsAccount_Base {

    public SavingsAccount(Bank bank) {
        setIBAN(bank.getCode() + ++Account.counter);
        setBalance(0);
        setBank(bank);
    }

    public SavingsAccount(Bank bank, int amount) {
        setIBAN(bank.getCode() + ++Account.counter);
        setBalance(amount);
        setBank(bank);
    }

    @Override
    public void deposit(int amount) {
        if (amount % 100 == 0)
            setBalance(getBalance() + amount);
    }

    @Override
    public void withdraw(int amount) {
        if (amount == getBalance())
            setBalance(0);
    }
}
