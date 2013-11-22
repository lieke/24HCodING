package nl.ing.cla.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import javax.ws.rs.core.Response;

import nl.ing.cla.db.GetData;
import nl.ing.cla.db.SaveData;
import nl.ing.cla.model.ChildAccount;
import nl.ing.cla.model.Chore;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ChildAccountServiceTest {
	
	
	@Mock
	private GetData getData;

	@Mock
	private SaveData saveData;
	
	@InjectMocks
	ChildAccountService childAccountService = new ChildAccountService();
	
	ChildAccount ca = null;
	
	@Before
	public void setup(){
		ca = new ChildAccount();
		ca.setAccountNumber("C001");
		ca.setAge(6);
		ca.setBalance(20.0);
		//ca.setChoreList(choreList);
		ca.setName("LISA");
		when(getData.getChildAccountData("LISA")).thenReturn(ca);
		Mockito.doNothing().when(saveData).saveChildAccountData(ca);
	}
	
	@Test
	public void testGetChild(){
		ChildAccount childAcount = childAccountService.getTrackInJSON("LISA");
		assertNotNull(childAcount);
		assertEquals(childAcount.getName(), "LISA");
	}
	
	@Test
	public void testCreateChoreForChild(){
		Chore chore = new Chore();
		chore.setName("car wash");
		chore.setPrice(2.5);
		chore.setDate("20 Nov 2013");
		chore.setStatus(0);
		Response response = childAccountService.createChoreForChild("LISA", chore);
		assertNotNull(response);
		assertEquals(ca.getChoreList().values().size(), 1);
	}
	

}
