package nl.ing.cla.model;

import java.util.ArrayList;
import java.util.List;

public class DataFileBasedParentAccount extends ParentAccountBase{	
	List<String> childNames = new ArrayList<String>();
	
	public List<String> getChildNames() {
		return childNames;
	}
	public void setChildNames(List<String> childNames) {
		this.childNames = childNames;
	}
}
