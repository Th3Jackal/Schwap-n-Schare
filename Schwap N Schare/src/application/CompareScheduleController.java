package application;

import java.io.IOException;

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
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CompareScheduleController {
//initialize variables for comparing schedules
//load in other users' schedules and display 
//display code for schedule compare page 
	private Stage stage;
	private Scene scene;
	private Parent root;
	private GraphicsContext gc1,gc2;
	
    @FXML
    private TextField file2;

    @FXML
    private TextField file1;

    @FXML
    private Canvas canvas1;

    @FXML
    private Canvas canvas2;
    
    public void loadFiles() {
    	Main.schedule1Name = file1.getText();
    	Main.schedule2Name = file2.getText();
    	draw();
    }
    
	public void initialize() {
		file1.setText(Main.schedule1Name);
		file2.setText(Main.schedule2Name);
		gc1 = canvas1.getGraphicsContext2D();
		gc2 = canvas2.getGraphicsContext2D();
		draw();
	}
    
	public void draw() {
		try {
			Main.schedule1.readFromFile(Main.schedule1Name);
		}catch(Exception e) {
			return;
		}
		try {
			Main.schedule2.readFromFile(Main.schedule2Name);
		}catch(Exception e) {
			return;
		}
		double ratio = 227.0/304.0;
		gc1.setFill(Color.WHITE);
		gc1.fillRect(0, 0, 577.0, 304.0);
		gc1.setFill(Color.BLACK);
		gc1.setFont(Font.font("Sans Serif", 9.89142857143));
		gc1.strokeLine(0,22.2557142857*ratio,577.0,22.2557142857*ratio);
		gc2.setFill(Color.WHITE);
		gc2.fillRect(0, 0, 577.0, 304.0);
		gc2.setFill(Color.BLACK);
		gc2.setFont(Font.font("Sans Serif", 9.89142857143));
		gc2.strokeLine(0,22.2557142857*ratio,577.0,22.2557142857*ratio);
		for(int i=0; i<7; i++) {
			gc1.strokeLine(82.42857142857143*i,0, 82.42857142857143*i, 304.0*ratio);
			gc1.setFont(Font.font("Sans Serif", 12.3642857143));
			gc1.fillText(ScheduleDate.days[i], 2+i*82.42857142857143, 82.42857142857143*.19*ratio);
			gc1.setFont(Font.font("Sans Serif", 9.89142857143));
			gc2.strokeLine(82.42857142857143*i,0, 82.42857142857143*i, 304.0*ratio);
			gc2.setFont(Font.font("Sans Serif", 12.3642857143));
			gc2.fillText(ScheduleDate.days[i], 2+i*82.42857142857143, 82.42857142857143*.19*ratio);
			gc2.setFont(Font.font("Sans Serif", 9.89142857143));
			for(ScheduleEvent se : Main.schedule1.getEvents(i)) {
				double eheight = (32.1471428571+243*(double)(se.getDate().getRealTime())/86400.0)*ratio;
				String name=se.getName(),date=se.getDate().getTime(),location=se.getLocation();
				if(name.length() > 13)
					name=name.substring(0,11)+"...";
				if(location.length() > 13)
					location=location.substring(0,11)+"...";
				gc1.fillText(name, 2+i*82.42857142857143, eheight);
				gc1.fillText(date, 2+i*82.42857142857143, eheight+10);
				gc1.fillText(location,2+i*82.42857142857143, eheight+20);
			}
			for(ScheduleEvent se : Main.schedule2.getEvents(i)) {
				double eheight = (32.1471428571+243*(double)(se.getDate().getRealTime())/86400.0)*ratio;
				String name=se.getName(),date=se.getDate().getTime(),location=se.getLocation();
				if(name.length() > 13)
					name=name.substring(0,11)+"...";
				if(location.length() > 13)
					location=location.substring(0,11)+"...";
				gc2.fillText(name, 2+i*82.42857142857143, eheight);
				gc2.fillText(date, 2+i*82.42857142857143, eheight+10);
				gc2.fillText(location,2+i*82.42857142857143, eheight+20);
			}
		}
	}
	
	public void switchToMain(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}
