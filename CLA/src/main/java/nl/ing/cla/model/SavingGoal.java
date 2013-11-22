package nl.ing.cla.model;


public class SavingGoal {
	double saved;
	double goal;	
	long id;
	String name;
	boolean show;
	
		
	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	public SavingGoal() {
		
	}	
		
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	public void setSaved(double saved) {
		this.saved = saved;
	}
	public double getSaved() {
		return saved;
	}
	public double getGoal() {
		return goal;
	}
	public void setGoal(double goal) {
		this.goal = goal;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
