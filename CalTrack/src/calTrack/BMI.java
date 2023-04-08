package calTrack;

public class BMI {
	public double calc(double mass, double height)
	{
		double height2= height * height;
		double bmi= mass / height2;
		return bmi;
	}

}