package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
//Main class extends the Application package 
//other users' schedule files are read into the class
	public static Schedule schedule1,schedule2;
	public static String schedule1Name = "schedule.txt", schedule2Name="schedule_.txt";
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Main.fxml"));
			Scene scene = new Scene(root,600,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Schwap N Schare");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		schedule1 = new Schedule();
		try {
		schedule1.readFromFile(Main.schedule1Name);
		}catch(Exception e) {}
		schedule2 = new Schedule();
		try {
			schedule2.readFromFile(Main.schedule1Name);
		}catch(Exception e) {}
		
		launch(args);
	}
}
