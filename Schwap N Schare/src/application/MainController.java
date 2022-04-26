package application;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController {
//when user switches between the pages on the application
//functions called to switch to a different page and display accordingly
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void switchToEdit(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("EditSchedule.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToCompare(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("CompareSchedule.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToProfile(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToSettings(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Settings.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToView(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("ViewSchedule.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
}
