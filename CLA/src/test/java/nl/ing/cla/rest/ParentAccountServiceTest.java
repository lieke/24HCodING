package nl.ing.cla.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nl.ing.cla.db.GetData;
import nl.ing.cla.db.SaveData;
import nl.ing.cla.exception.BalanceToLowToTransferMoneyException;
import nl.ing.cla.model.ChildAccount;
import nl.ing.cla.model.Chore;
import nl.ing.cla.model.DataFileBasedParentAccount;
import nl.ing.cla.model.ParentAccount;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ParentAccountServiceTest {
	
	
	@Mock
	private GetData getData;

	@Mock
	private SaveData saveData;
	
	@InjectMocks
	ParentAccountService parentAccountService = new ParentAccountService();
	
	ParentAccount pa = null;
	
	DataFileBasedParentAccount dataFileBasedParentAccount = null;
	
	ChildAccount ca = null;
	
	@Before
	public void setup() throws JsonGenerationException, JsonMappingException, IOException{
		dataFileBasedParentAccount = new DataFileBasedParentAccount();
		dataFileBasedParentAccount.setAccountNumber("P001");
		dataFileBasedParentAccount.setAge(34);
		dataFileBasedParentAccount.setBalance(2000.0);
		dataFileBasedParentAccount.setName("FRANCOIS");
		
		ca = new ChildAccount();
		ca.setAccountNumber("C001");
		ca.setAge(6);
		ca.setBalance(20.0);
		ca.setName("LISA");
		List<ChildAccount> caList = new ArrayList<ChildAccount>();
		caList.add(ca);
		pa = new ParentAccount(dataFileBasedParentAccount, caList);
		
		Chore chore = new Chore();
		chore.setName("car wash");
		chore.setPrice(2.5);
		chore.setDate("20 Nov 2013");
		chore.setStatus(0);
		ca.addChore(chore);		
		
		when(getData.getParentAccountData("FRANCOIS")).thenReturn(pa);
		when(getData.getChildAccountData("LISA")).thenReturn(ca);
		Mockito.doNothing().when(saveData).saveChildAccountData(ca);
		Mockito.doNothing().when(saveData).saveDataFileBasedParentAccountData(dataFileBasedParentAccount);
	}
	

	

	@Test
	public void testGetParent(){
		ParentAccount parentAcount = parentAccountService.getTrackInJSON("FRANCOIS");
		assertNotNull(parentAcount);
		assertEquals(parentAcount.getName(), "FRANCOIS");
		assertNotNull(parentAcount.getChildaccounts().get(0));
		assertEquals(parentAcount.getChildaccounts().get(0).getName(), "LISA");
	}
	
	
	@Test
	public void testCompleteChore() throws BalanceToLowToTransferMoneyException{
		long choreId = pa.getChildaccounts().get(0).getChoreList().values().iterator().next().getId();
		parentAccountService.completeChoreForChild("FRANCOIS", choreId, "LISA");
		assertEquals(pa.getBalance() == 1997.5, true);
		assertEquals(ca.getBalance() == 22.5, true);
	}
	
	
}
