package pt.ulisboa.tecnico.softeng.bank.domain;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Test;

import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.Atomic.TxMode;
import pt.ist.fenixframework.FenixFramework;

public class BankPersistenceTest {
	@Test
	public void success() {
		atomicProcess();
		atomicAssert();
	}

	@Atomic(mode = TxMode.WRITE)
	public void atomicProcess() {
		Bank bank = new Bank("Money", "BK01");
        Account account1 = new WithdrawalAccount(bank);
        Account account2 = new WithdrawalAccount(bank, 100);
        Account account3 = new SavingsAccount(bank);
        Account account4 = new SavingsAccount(bank, 100);
        account1.deposit(100);
        account2.withdraw(50);
        account3.deposit(100);
        account3.deposit(50);
        account4.withdraw(50);
        account4.withdraw(100);
	}

	@Atomic(mode = TxMode.READ)
	public void atomicAssert() {
		Bank bank = Bank.getBankByCode("BK01");

		assertEquals("Money", bank.getName());

        assertEquals(4, bank.getAccountSet().size());
        Account account1 = bank.getAccountByIBAN("BK011");
        Account account2 = bank.getAccountByIBAN("BK012");
        Account account3 = bank.getAccountByIBAN("BK013");
        Account account4 = bank.getAccountByIBAN("BK014");
        assertEquals(100, account1.getBalance());
        assertEquals(50, account2.getBalance());
        assertEquals(250, bank.totalBalance());
        assertEquals(100, account3.getBalance());
        assertEquals(0, account4.getBalance());
	}

	@After
	@Atomic(mode = TxMode.WRITE)
	public void tearDown() {
		for (Bank bank : FenixFramework.getDomainRoot().getBankSet()) {
			bank.delete();
		}
	}

}
