package application;

import java.io.IOException;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class EditScheduleController {
//initialize for schedule edit page
//add and delete functions for each schedule activities defined 
	private Stage stage;
	private Scene scene;
	private Parent root;
	@FXML
    private TextField timeText;

    @FXML
    private TextField nameText;

    @FXML
    private TextField dayText;
    
    @FXML
    private TextField locationText;

    
    @FXML
    void addActivity(ActionEvent event) {
    	ScheduleDate sd = new ScheduleDate(0,0,0,0);
    	sd.parseString(dayText.getText()+" "+timeText.getText());
    	ScheduleEvent se = new ScheduleEvent(nameText.getText(), sd, locationText.getText());
    	Main.schedule1.addEvent(se);
    	Main.schedule1.writeToFile(Main.schedule1.getFilename());
    }

    @FXML
    void removeActivity(ActionEvent event) {
    	ScheduleDate sd = new ScheduleDate(0,0,0,0);
    	sd.parseString(dayText.getText()+" "+timeText.getText());
    	ScheduleEvent se = new ScheduleEvent(nameText.getText(), sd, locationText.getText());
    	Main.schedule1.removeEvent(se);
    	Main.schedule1.writeToFile(Main.schedule1.getFilename());
    }
    
	public void switchToMain(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}
