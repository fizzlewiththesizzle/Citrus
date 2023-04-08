package calTrack;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;


public class CalTrackGUI extends Application {
	Scene scenem, sceneb, scenec, scenew, scenedc, scenedcM, scenedcF, sceneS;
	public double mass, height, massM, heightM, massF, heightF;
	public int calorie, H2O, ageM, ageF;
	public String food;
	public ObservableList<Food> foods= FXCollections.observableArrayList();

	public void start(Stage primaryStage) throws Exception {
		
		BMI bmi= new BMI();
		primaryStage.setTitle("Citrus");
		primaryStage.getIcons().add(new Image("file:CalTrack/citrus-icon.png"));
		Path current = Paths.get(".");
		System.out.print(current.toAbsolutePath());

		Image image = new Image("file:CalTrack/citrus header_small.png"); 
		ImageView logo = new ImageView(image); 
	    logo.setFitHeight(400); 
	    logo.setFitWidth(400);
	    logo.setPreserveRatio(true);
		
		Button bbmi= new Button("BMI CALCULATOR");
		bbmi.setOnAction(e -> primaryStage.setScene(sceneb));  
		
		Button bcal= new Button("CALORIE TRACKER");
		bcal.setOnAction(e -> primaryStage.setScene(scenec));
		
		Button bH2O= new Button("WATER TRACKER");
		bH2O.setOnAction(e -> primaryStage.setScene(scenew));
		
		Button bdc= new Button("DAILY CALORIES");
		bdc.setOnAction(e -> primaryStage.setScene(scenedc));
		
		Button bsum= new Button("DAILY SUMMARY");
		bsum.setOnAction(e -> primaryStage.setScene(sceneS));
		
		VBox layoutd= new VBox(20);
		layoutd.setAlignment(Pos.CENTER);
		layoutd.getChildren().addAll(logo, bbmi, bcal, bH2O, bdc, bsum);
		scenem= new Scene(layoutd, 360, 640);
		scenem.getStylesheets().add("citrus.css");
		scenem.getStylesheets().add("https://fonts.googleapis.com/css?family=Roboto");
		
		//Scene BMI
		Label bmidsp= new Label("Your BMI will be displayed here");
		Label kgmsg= new Label("Enter your mass in kilograms (kg):");
		Label mtmsg= new Label("Enter your height in meters (m):");
		
		Button backb= new Button("Back");
		Button gob= new Button("Go");
		backb.setOnAction(e -> primaryStage.setScene(scenem)); 
		
		TextField masst= new TextField();
		TextField hgtt= new TextField();
		masst.setStyle("-fx-text-fill: #FFFFFF;");
		hgtt.setStyle("-fx-text-fill: #FFFFFF;");
		masst.setTextFormatter(new TextFormatter<>(new DoubleStringConverter())); 
		hgtt.setTextFormatter(new TextFormatter<>(new DoubleStringConverter())); 
		
		gob.setOnAction(new EventHandler<ActionEvent>() {  
		    
		    public void handle(ActionEvent arg0) {    
		    	try {
		    		mass= Double.parseDouble(masst.getText());
		    		height= Double.parseDouble(hgtt.getText());
		    		double finalbmi= bmi.calc(mass, height);
		    		finalbmi = Math.round(finalbmi * 100.0) / 100.0;
		    		String bmimsg= String.valueOf(finalbmi);
		    		bmidsp.setText("Your BMI is " + bmimsg);
		    		}
		    	catch(NumberFormatException ee)
		    	{
		    		String eep= "Textfields cannot be empty";
		    		System.out.println(eep);
		    	}
		    }  
		} );  
		
		VBox layoutb= new VBox(20);
		layoutb.setAlignment(Pos.CENTER);
		layoutb.getChildren().addAll(kgmsg, masst, mtmsg, hgtt, gob, backb, bmidsp);
		sceneb= new Scene(layoutb, 360, 640);
		sceneb.getStylesheets().add("citrus.css");
		
		//Scene Calories
		CalorieTrack ctrack= new CalorieTrack();
		Label fdmsg= new Label("Enter what item you ate here");
		Label clmsg= new Label("Enter the item's calories here");
		Label ccdsp= new Label("0");
		Label ccmsg= new Label("calories");
		ccdsp.setStyle("-fx-text-fill: #FF6F00; -fx-font-size: 30.0px;");
		ccmsg.setStyle("-fx-text-fill: #FF6F00; -fx-font-size: 20.0px;");
		
		Button backc= new Button("Back");
		Button goc= new Button("Go");
		backc.setOnAction(e -> primaryStage.setScene(scenem)); 
		
		TextField foodt= new TextField();
		TextField calt= new TextField();
		foodt.setStyle("-fx-text-fill: #FFFFFF;");
		calt.setStyle("-fx-text-fill: #FFFFFF;");
		calt.setTextFormatter(new TextFormatter<>(new IntegerStringConverter())); 
		
		goc.setOnAction(new EventHandler<ActionEvent>() {  
		    
		    public void handle(ActionEvent arg0) {    
		    	try {
		    		food= foodt.getText();
		    		calorie= Integer.parseInt(calt.getText());
		    		ctrack.input(food, calorie);
		    		int finalCal= ctrack.calCount;
		    		String totalCal= String.valueOf(finalCal);
		    		ccdsp.setText(totalCal);
		    		foodt.clear();
		    		calt.clear();
		    		ctrack.write();
		    		foods.add(new Food(food, calorie));
		    		}
		    	catch(NumberFormatException | FileNotFoundException ee)
		    	{
		    		String eep= "Textfields cannot be empty";
		    		System.out.println(eep);
		    	}
		    }  
		} );  
		
		VBox layoutc= new VBox(20);
		layoutc.setAlignment(Pos.CENTER);
		layoutc.getChildren().addAll(ccdsp, ccmsg, fdmsg, foodt, clmsg, calt, goc, backc);
		scenec= new Scene(layoutc, 360, 640);
		scenec.getStylesheets().add("citrus.css");
		
		//Scene Water
		Button backw= new Button("Back");
		Button savew= new Button("Save");
		Label H2Omsg= new Label("How many glasses of water have you had today?");
		Label H2Ods= new Label("0 glasses of water today");
		backw.setOnAction(e -> primaryStage.setScene(scenem)); 
		
		Spinner <Integer> spinH2O= new Spinner<Integer>();
		int H2Oi= 0;
		SpinnerValueFactory<Integer> H2Ovf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 12, H2Oi);
		spinH2O.setValueFactory(H2Ovf); 
		spinH2O.getStyleClass().add("split-arrows-horizontal");
		spinH2O.setStyle("-fx-align: center;");
		savew.setOnAction(new EventHandler<ActionEvent>() {  
		    
		    public void handle(ActionEvent arg0) {    
		    	H2O= spinH2O.getValue();
		    	try {
					ctrack.saveH2O(H2O);
					H2Ods.setText(H2O + " glasses of water today");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }  
		} );  
		
		VBox layoutw= new VBox(20);
		layoutw.setAlignment(Pos.CENTER);
		layoutw.getChildren().addAll(H2Omsg, spinH2O, savew, backw);
		scenew= new Scene(layoutw, 360, 640);
		scenew.getStylesheets().add("citrus.css");
		
		//Scene DailyCalories
		Label dcal= new Label("Your daily reccomended calorie intake is based on the level of your physical activity, your age, your BMI, and your gender.");
		dcal.setWrapText(true);
		Button backdc= new Button("Back");
		Button dcM= new Button("Male");
		Button dcF= new Button("Female");
		backdc.setOnAction(e -> primaryStage.setScene(scenem)); 
		dcM.setOnAction(e -> primaryStage.setScene(scenedcM)); 
		dcF.setOnAction(e -> primaryStage.setScene(scenedcF)); 

		VBox layoutdc= new VBox(20);
		layoutdc.setAlignment(Pos.CENTER);
		layoutdc.getChildren().addAll(dcal, dcM, dcF, backdc);
		scenedc= new Scene(layoutdc, 360, 640);
		scenedc.getStylesheets().add("citrus.css");
		
		//Scene DailyCalories Male
		DailyCalorie dCal= new DailyCalorie();
		
		Label kgM= new Label("Enter your mass in kilograms (kg):");
		Label hgtM= new Label("Enter your height in meters (m):");       //remember to convert meters to centimeters so that formula works correctly
		Label ageMl= new Label("Enter your age:");
		Label alM= new Label("Activity Level");
		Label actM= new Label("Sedentary | Light | Moderate | High | Vigorous");
		Label calM= new Label("0 | 0 | 0 | 0 | 0");


		Button backdcM= new Button("Back");
		Button godcM= new Button("Go");
		backdcM.setOnAction(e -> primaryStage.setScene(scenedc)); 
		
		TextField masstM= new TextField();
		TextField hgttM= new TextField();
		TextField agetM= new TextField();
		masstM.setStyle("-fx-text-fill: #FFFFFF;");
		hgttM.setStyle("-fx-text-fill: #FFFFFF;");
		agetM.setStyle("-fx-text-fill: #FFFFFF;");
		masstM.setTextFormatter(new TextFormatter<>(new DoubleStringConverter())); 
		hgttM.setTextFormatter(new TextFormatter<>(new DoubleStringConverter())); 
		agetM.setTextFormatter(new TextFormatter<>(new IntegerStringConverter())); 
		
		godcM.setOnAction(new EventHandler<ActionEvent>() {  
		    
		    public void handle(ActionEvent arg0) {    
		    	massM= Double.parseDouble(masstM.getText());
		    	heightM= Double.parseDouble(hgttM.getText());
		    	ageM= Integer.parseInt(agetM.getText());
		    	double dCalM= dCal.male(massM, heightM, ageM);
		    	double saM= dCalM * 1.2;
		    	double laM= dCalM * 1.375;
		    	double maM= dCalM * 1.55;
		    	double haM= dCalM * 1.725;
		    	double vaM= dCalM * 1.9;
		    	saM= Math.round(saM * 100.0) / 100.0;
		    	laM= Math.round(laM * 100.0) / 100.0;
		    	maM= Math.round(maM * 100.0) / 100.0;
		    	haM= Math.round(haM * 100.0) / 100.0;
		    	vaM= Math.round(vaM * 100.0) / 100.0;
		    	calM.setText(saM + " | " + laM + " | " + maM + " | " + haM + " | " + vaM);
		    	System.out.println("BMR: " + dCalM);
		    }  
		} );  
		
		VBox layoutdcM= new VBox(20);
		layoutdcM.setAlignment(Pos.CENTER);
		layoutdcM.getChildren().addAll(kgM, masstM, hgtM, hgttM, ageMl, agetM, godcM, alM, actM, calM, backdcM);
		scenedcM= new Scene(layoutdcM, 360, 640);
		scenedcM.getStylesheets().add("citrus.css");
		
		//Scene DailyCalories Female
		Label kgF= new Label("Enter your mass in kilograms (kg):");
		Label hgtF= new Label("Enter your height in meters (m):");       //remember to convert meters to centimeters so that formula works correctly
		Label ageFl= new Label("Enter your age:");
		Label alF= new Label("Activity Level");
		Label actF= new Label("Sedentary | Light | Moderate | High | Vigorous");
		Label calF= new Label("0 | 0 | 0 | 0 | 0");
		
		Button backdcF= new Button("Back");
		Button godcF= new Button("Go");
		backdcF.setOnAction(e -> primaryStage.setScene(scenedc)); 
		
		TextField masstF= new TextField();
		TextField hgttF= new TextField();
		TextField agetF= new TextField();
		masstF.setStyle("-fx-text-fill: #FFFFFF;");
		hgttF.setStyle("-fx-text-fill: #FFFFFF;");
		agetF.setStyle("-fx-text-fill: #FFFFFF;");
		masstF.setTextFormatter(new TextFormatter<>(new DoubleStringConverter())); 
		hgttF.setTextFormatter(new TextFormatter<>(new DoubleStringConverter())); 
		agetF.setTextFormatter(new TextFormatter<>(new IntegerStringConverter())); 
		
		godcF.setOnAction(new EventHandler<ActionEvent>() {  
		    
		    public void handle(ActionEvent arg0) {    
		    	massF= Double.parseDouble(masstF.getText());
		    	heightF= Double.parseDouble(hgttF.getText());
		    	ageF= Integer.parseInt(agetF.getText());
		    	double dCalF= dCal.female(massF, heightF, ageF);
		    	double saF= dCalF * 1.2;
		    	double laF= dCalF * 1.375;
		    	double maF= dCalF * 1.55;
		    	double haF= dCalF * 1.725;
		    	double vaF= dCalF * 1.9;
		    	saF= Math.round(saF * 100.0) / 100.0;
		    	laF= Math.round(laF * 100.0) / 100.0;
		    	maF= Math.round(maF * 100.0) / 100.0;
		    	haF= Math.round(haF * 100.0) / 100.0;
		    	vaF= Math.round(vaF * 100.0) / 100.0;
		    	calF.setText(saF + " | " + laF + " | " + maF + " | " + haF + " | " + vaF);
		    	System.out.println("BMR: " + dCalF);
		    }  
		} );  

		VBox layoutdcF= new VBox(20);
		layoutdcF.setAlignment(Pos.CENTER);
		layoutdcF.getChildren().addAll(kgF, masstF, hgtF, hgttF, ageFl, agetF, godcF, alF, actF, calF, backdcF);
		scenedcF= new Scene(layoutdcF, 360, 640);
		scenedcF.getStylesheets().add("citrus.css");
		
		//Scene Daily Summary
		Label ts= new Label("Today's Summary");
		Button backds= new Button("Back");
		backds.setOnAction(e -> primaryStage.setScene(scenem)); 

		TableView<Food> table= new TableView<Food>();
		
		TableColumn<Food, String> foodCol= new TableColumn<>("Item");
		foodCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		TableColumn<Food, Integer> calCol= new TableColumn<>("Calories");
		calCol.setCellValueFactory(new PropertyValueFactory<>("energy"));
		
		table.setPlaceholder(new Label("No items have been saved yet"));
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table.setItems(foods);
		table.getColumns().addAll(foodCol, calCol);
		table.setStyle("-fx-selection-bar: #ffcca4;");

		VBox layoutS= new VBox(20);
		layoutS.setAlignment(Pos.CENTER);
		layoutS.getChildren().addAll(ts, table, H2Ods, backds);
		sceneS= new Scene(layoutS, 360, 640);
		sceneS.getStylesheets().add("citrus.css");
		
		primaryStage.setScene(scenem);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
		}
}