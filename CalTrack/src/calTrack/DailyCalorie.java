package calTrack;

public class DailyCalorie {
	
	CalTrackGUI ctgui= new CalTrackGUI();
	public double male(double massM, double heightM, int ageM)
	{
		double cm= heightM * 100;
		double dcal= 10 * massM + 6.25 * cm - 5 * ageM + 5;
		return dcal;
	}
	
	public double female(double massF, double heightF, int ageF)
	{
		double cm= heightF * 100;
		double dcal= 10 * massF + 6.25 * cm - 5 * ageF - 161;
		return dcal;
	}

}