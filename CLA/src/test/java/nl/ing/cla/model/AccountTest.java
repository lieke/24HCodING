package nl.ing.cla.model;

import static org.junit.Assert.assertTrue;
import nl.ing.cla.exception.BalanceToLowToTransferMoneyException;
import nl.ing.cla.model.Account;
import nl.ing.cla.model.ChildAccount;

import org.junit.Test;

public class AccountTest {

	Account acc1 = new ChildAccount();
	Account acc2 = new ChildAccount();

	@Test
	public void testTransferTo() throws BalanceToLowToTransferMoneyException {
		acc1.setBalance(100);
		acc2.setBalance(300);
		acc2.transferTo(20, acc1);
		assertTrue(acc2.getBalance() == 280);
		assertTrue(acc1.getBalance() == 120);
	}
	
	@Test (expected = BalanceToLowToTransferMoneyException.class)
	public void testTransferToNotEnoughBalance() throws Exception {
		acc1.setBalance(100);
		acc2.setBalance(10);
		acc2.transferTo(20, acc1);
		assertTrue(acc1.getBalance() == 100);
		assertTrue(acc2.getBalance() == 10);
	}
}
