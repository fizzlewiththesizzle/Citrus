package calTrack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;

public class CalorieTrack {
	CalTrackGUI ctgui= new CalTrackGUI();
	
	public int calCount;
	public HashMap<String, Integer> calmap = new HashMap<String, Integer>();
	
	public void input(String food, int calorie)
	{
		System.out.println(food + ": " + calorie);
		calmap.put(food, calorie);
		System.out.println(calmap);
		
		calCount= calmap.entrySet().stream().mapToInt(entry -> entry.getValue()).sum();
		System.out.println(calCount);
	}
	
	public void write() throws FileNotFoundException
	{
		PrintWriter pw= new PrintWriter("calories.txt");
		for (String i : calmap.keySet()) { 
			pw.println(i + ": " + calmap.get(i)); 	
			//pw.println("Total: " + calCount);
		}
		
		pw.close();
	}
	
	public void saveH2O(int H2O) throws FileNotFoundException
	{
		PrintWriter pw= new PrintWriter("D:\\JavaPhoton Tings\\CalTrack Output\\water.txt");
		pw.println("Glasses of Water Today: " + H2O);
		pw.close();
		System.out.println("Glasses of Water Today: " + H2O);
	}

}