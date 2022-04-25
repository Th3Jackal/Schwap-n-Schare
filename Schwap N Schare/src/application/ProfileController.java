package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import java.util.Arrays;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class ProfileController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	private Label name;
	@FXML
	private Label username;
	@FXML
	public TextArea bio;
	@FXML
	public TextArea classes;
	
	//Lists all of the relevant profile data on initialization of the scene
	@FXML
	public void initialize() {
		
		//This makes the text areas to neither scroll horizontally nor be edited
		bio.setEditable(false);
		classes.setEditable(false);
		bio.setWrapText(true);
		classes.setWrapText(true);
		
		//This loads the name and username from name.txt to display them
		File file = new File(System.getProperty("user.dir") + "/src/" +  "name.txt");
		try {
			FileReader reader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(reader);
			
			String line;

			int i = 1;
			
			while ((line = bufferedReader.readLine()) != null) {
				if (i == 1) {
					name.setText(line);
				}
				else if (i == 2) {
					username.setText(line);
				}
				i++;
			}
			reader.close();
		} catch (FileNotFoundException e) {
			name.setText("Name");
			username.setText("Username");
		} catch (Exception e) {
			System.out.print(e);
		}
		
		//This loads the biography from bio.txt to display it
		File file2 = new File(System.getProperty("user.dir") + "/src/" +  "bio.txt");
		try {
			FileReader reader = new FileReader(file2);
			BufferedReader bufferedReader = new BufferedReader(reader);
			
			String line;
			
			while ((line = bufferedReader.readLine()) != null) {
				bio.appendText(line);
				bio.appendText("\r\n");
			}
			
			reader.close();
		} catch (FileNotFoundException e) {
			bio.setText("");
		} catch (Exception e) {
			System.out.print(e);
		}
		
		//This loads the class list from classes.txt to display it
		File file3 = new File(System.getProperty("user.dir") + "/src/" +  "classes.txt");
		try {
			FileReader reader = new FileReader(file3);
			BufferedReader bufferedReader = new BufferedReader(reader);
			
			String line;
			
			while ((line = bufferedReader.readLine()) != null) {
				classes.appendText(line);
				classes.appendText("\r\n");
			}

			reader.close();
		} catch (FileNotFoundException e) {
			classes.setText("");
		} catch (Exception e) {
			System.out.print(e);
		}
		
	}
	
	//This enables switching back to the menu from the profile
	public void switchToMain(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	//This allows the user to close the app when clicking the logout button
	public void Logout(ActionEvent event) throws IOException {
		System.exit(0);
	}

}
