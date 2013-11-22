package nl.ing.cla.db.model;

import static org.junit.Assert.assertTrue;
import nl.ing.cla.model.ChildAccount;
import nl.ing.cla.model.Chore;

import org.junit.Before;
import org.junit.Test;

public class ChildAccountTest {

	ChildAccount account;
	Chore chore;
	
	@Before
	public void setup() {
		account = new ChildAccount();
		chore = new Chore();
		chore.setId(1);
		chore.setStatus(Chore.NEW_STATUS);		
		account.addChore(chore);
	}

	@Test
	public void setChoreAsDoneAndThenAsPaidTest() {
		account.setChoreAsDone(1);
		assertTrue(account.getChore(1).getStatus() == Chore.DONE_STATUS);
		account.setChoreAsPaid(1);
		assertTrue(account.getChore(1).getStatus() == Chore.PAID_STATUS);
	}
}
