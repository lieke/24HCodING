package nl.ing.cla.model;

public class SavingGoal {
	float saved;
	float goal;
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
	public void setSaved(float saved) {
		this.saved = saved;
	}
	public float getSaved() {
		return saved;
	}
	public float getGoal() {
		return goal;
	}
	public void setGoal(float goal) {
		this.goal = goal;
	}

	
}
