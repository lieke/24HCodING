package nl.ing.cla.model;


public class SavingGoal {
	double saved;
	double goal;	
	long id;
	
	public SavingGoal() {
		
	}
	
	public SavingGoal(float saved, float goal, long id) {
		super();
		this.saved = saved;
		this.goal = goal;
		this.id = id;
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

	
}
