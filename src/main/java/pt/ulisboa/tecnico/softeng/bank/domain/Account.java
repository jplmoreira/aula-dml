package pt.ulisboa.tecnico.softeng.bank.domain;

public class Account extends Account_Base {

    protected static int counter = 0;
    
    public Account() {
        super();
    }
    
    public Account(Bank bank) {
        setIBAN(bank.getCode() + ++Account.counter);
        setBalance(0);
        setBank(bank);
    }

    public Account(Bank bank, int amount) {
        setIBAN(bank.getCode() + ++Account.counter);
        setBalance(amount);
        setBank(bank);
    }

    public void delete() {
        setBank(null);

        deleteDomainObject();
    }

    public void deposit(int amount) {
        setBalance(getBalance() + amount);
    }

    public void withdraw(int amount) {
        if (amount > getBalance())
            return;
        setBalance(getBalance() - amount);
    }
    
}
