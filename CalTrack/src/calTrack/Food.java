package calTrack;

public class Food {
	
	private String name;
	private int energy;
	
	public Food()
	{
		this.name= " ";
		this.energy= 0;
	}

	public Food(String name, int energy) {
		this.name = name;
		this.energy = energy;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}
	
}