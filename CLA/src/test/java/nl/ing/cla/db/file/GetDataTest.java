package nl.ing.cla.db.file;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nl.ing.cla.model.ChildAccount;
import nl.ing.cla.model.DataFileBasedParentAccount;
import nl.ing.cla.model.ParentAccount;
import nl.ing.cla.util.CLAUtil;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GetDataTest {
	
	@Mock
	private CLAUtil util;
	
	@InjectMocks
	FileGetData getData = new FileGetData();
	

	ObjectMapper mapper = new ObjectMapper(){
		public <T extends Object> T readValue(File file, java.lang.Class<T> valueType) throws IOException ,JsonParseException ,JsonMappingException {
			if(valueType == ChildAccount.class){
				return (T)ca;
			}else{
				return (T)dataFileBasedParentAccount;
			}
		};
	};
	
	ChildAccount ca = null;
	
	ParentAccount pa = null;
	
	DataFileBasedParentAccount dataFileBasedParentAccount = null;
	
	
	
	@Before
	public void setUp() throws Exception{
		
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
		
		ca = new ChildAccount();
		ca.setAccountNumber("C001");
		ca.setAge(6);
		ca.setBalance(20.0);
		ca.setName("LISA");
		getData.setMapper(mapper);
		
		when(util.getFileName(isA(String.class))).thenReturn("ABC");
		
	}
	
	@Test
	public void testGetChildAccountData(){
		ChildAccount ca1 = getData.getChildAccountData("LISA");
		assertNotNull(ca1);
		assertEquals(ca1.getName(), "LISA");
	}

	@Test
	public void testGetParentAccountData() throws Exception{
		ParentAccount pa1 = getData.getParentAccountData("FRANCOIS");
		assertNotNull(pa1);
		assertEquals(pa1.getName(), "FRANCOIS");
	}
	
}
