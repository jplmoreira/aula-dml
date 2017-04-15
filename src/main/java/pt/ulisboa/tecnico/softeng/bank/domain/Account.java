package pt.ulisboa.tecnico.softeng.bank.domain;

public abstract class Account extends Account_Base {

    protected static int counter = 0;
    
    public Account() {
        super();
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
