package nl.ing.cla.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.ws.rs.core.Response;

import nl.ing.cla.db.GetData;
import nl.ing.cla.db.SaveData;
import nl.ing.cla.exception.ErrorException;
import nl.ing.cla.model.ChildAccount;
import nl.ing.cla.model.Chore;
import nl.ing.cla.model.SavingGoal;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

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
	public void setup() throws JsonGenerationException, JsonMappingException, IOException{
		ca = new ChildAccount();
		ca.setAccountNumber("C001");
		ca.setAge(6);
		ca.setBalance(20.0);
		ca.setName("LISA");
		when(getData.getChildAccountData("LISA")).thenReturn(ca);
		Mockito.doNothing().when(saveData).saveChildAccountData(ca);
	}
	
	@Test
	public void testSaveDataWithException() throws JsonGenerationException, JsonMappingException, IOException {
		doThrow(new IOException()).when(saveData).saveChildAccountData(any(ChildAccount.class));
		try {
			childAccountService.createChoreForChild("LISA", new Chore());
		} catch (ErrorException e) {
			Response response = e.getResponse();
			assertEquals(response.getStatus(), Response.Status.BAD_REQUEST.getStatusCode());
		}		
		
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
	
	@Test
	public void testUpdateChore(){
		Chore chore = new Chore();
		chore.setName("car wash");
		chore.setPrice(2.5);
		chore.setDate("20 Nov 2013");
		chore.setStatus(0);
		ca.addChore(chore);
		
		Chore chore2 = new Chore();
		chore2.setName("car wash");
		chore2.setPrice(5.0);
		chore2.setDate("20 Nov 2013");
		chore2.setStatus(0);
		chore2.setId(chore.getId());
		
		double price = ca.getChoreList().values().iterator().next().getPrice();
		assertEquals(price == 2.5, true);
		
		childAccountService.updateOrCreateChoreForChild("LISA", chore.getId(), chore2);
		
		price = ca.getChoreList().values().iterator().next().getPrice();
		assertNotNull(price);
		assertEquals(price == 5.0, true);
	}
	
	
	@Test
	public void testDeleteChore(){
		Chore chore = new Chore();
		chore.setName("car wash");
		chore.setPrice(2.5);
		chore.setDate("20 Nov 2013");
		chore.setStatus(0);
		ca.addChore(chore);
		
		childAccountService.deleteChoreForChild("LISA", chore.getId());
		
		assertEquals(ca.getChoreList().isEmpty(), true);
	}
	
	@Test
	public void testCreateGoal(){
		SavingGoal goal = new SavingGoal();
		goal.setGoal(2.5);
		goal.setSaved(1.4);
		
		childAccountService.createSavingGoalForChild("LISA", goal);
		assertEquals(ca.getGoalList().values().isEmpty(), false);
	}

	@Test
	public void testUpdateGoal(){
		SavingGoal goal = new SavingGoal();
		goal.setGoal(2.5);
		goal.setSaved(1.4);
		ca.addGoal(goal);

		SavingGoal goal2 = new SavingGoal();
		goal2.setId(goal.getId());
		goal2.setGoal(2.5);
		goal2.setSaved(2.0);
		
		double saved = ca.getGoalList().values().iterator().next().getSaved();
		assertEquals(saved == 1.4, true);
		childAccountService.updateOrCreateSavingGoalForChild("LISA", goal2.getId(), goal2);
		saved = ca.getGoalList().values().iterator().next().getSaved();
		assertEquals(saved == 2.0, true);
		
	}
	
	@Test
	public void testDeleteSavingGoal(){
		SavingGoal goal = new SavingGoal();
		goal.setGoal(2.5);
		goal.setSaved(1.4);
		ca.addGoal(goal);
		
		childAccountService.deleteSavingGoalForChild("LISA", goal.getId());
		assertEquals(ca.getGoalList().values().isEmpty(), true);
	}
	
}
