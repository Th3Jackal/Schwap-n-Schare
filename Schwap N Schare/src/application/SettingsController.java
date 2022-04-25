package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SettingsController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	private TextField textField1;
	@FXML
	private TextField textField2;
	@FXML
	private Button myButton;
	@FXML
	private Button myButton2;
	@FXML
	private Button myButton3;
	@FXML
	private TextArea textArea1;
	@FXML
	private TextArea textArea2;
	
	//This enables the user to move back to the app's menu
	public void switchToMain(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	//This saves the name and username to name.txt
	public void save(ActionEvent event) {
	    try {
	    	File file = new File(System.getProperty("user.dir") + "/src/" +  "name.txt");
	    	FileWriter myWriter = new FileWriter(file);
	    	
	    	String name = textField1.getText();
	    	String username = textField2.getText();
	    	
	    	myWriter.write(name);
	    	myWriter.write("\r\n");
	    	myWriter.write(username);
	    	
	    	myWriter.close();
	    } catch (IOException e) {
	    	System.out.println(e);
	    	e.printStackTrace();
	    }
	}
	
	//This saves the entered biography to bio.txt
	public void saveBio(ActionEvent event) {
	    try {
	    	File file = new File(System.getProperty("user.dir") + "/src/" +  "bio.txt");
	    	FileWriter myWriter = new FileWriter(file);
	    	
	    	String bio = textArea1.getText();
	    	
	    	myWriter.write(bio);;
	    	
	    	myWriter.close();
	    } catch (IOException e) {
	    	System.out.println(e);
	    	e.printStackTrace();
	    }
	}
	
	//This saves the entered class list to classes.txt
	public void saveClass(ActionEvent event) {
	    try {
	    	File file = new File(System.getProperty("user.dir") + "/src/" +  "classes.txt");
	    	FileWriter myWriter = new FileWriter(file);
	    	
	    	String classes = textArea2.getText();
	    	
	    	myWriter.write(classes);
	    	
	    	myWriter.close();
	    } catch (IOException e) {
	    	System.out.println(e);
	    	e.printStackTrace();
	    }
	}
}