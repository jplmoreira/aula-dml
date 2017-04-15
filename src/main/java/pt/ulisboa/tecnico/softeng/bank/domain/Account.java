package pt.ulisboa.tecnico.softeng.bank.domain;

public class Account extends Account_Base {
    
    public Account(String name, Bank bank) {
        setName(name);
        setBank(bank);
    }
    
    public void delete() {
        setBank(null);

        deleteDomainObject();
    }
}
