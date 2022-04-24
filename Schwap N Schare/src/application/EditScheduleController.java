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
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class EditScheduleController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	private GraphicsContext gc;
	
    @FXML
    private Canvas canvas;
	
	@FXML
	public void initialize() {
		gc = canvas.getGraphicsContext2D();
		draw(gc);
	}
	
	public void draw(GraphicsContext gc) {
		
		Schedule schedule = new Schedule();
		schedule.addEvent("Assignment 4", 3, 16, 12, 5, "NPB 1.28");
		schedule.addEvent("Test", 6, 23, 59, 59, "Times Square");
		schedule.addEvent("Group Project", 0, 5, 00, 00, "Mckinley Hall");
		schedule.addEvent("Counselor appointment", 5, 13, 15, 00, "NPB 1.28");
		
		
		double width = gc.getCanvas().getWidth();
		double height = gc.getCanvas().getHeight();
		double columnWidth = width/7;
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, width, height);
		gc.setFill(Color.BLACK);
		gc.setFont(Font.font("Sans Serif", columnWidth*.12));
		gc.strokeLine(0,columnWidth*.27,width,columnWidth*.27);
		for(int i=0; i<7; i++) {
			gc.strokeLine(columnWidth*i,0, columnWidth*i, height);
			gc.setFont(Font.font("Sans Serif", columnWidth*.15));
			gc.fillText(ScheduleDate.days[i], 2+i*columnWidth, columnWidth*.19);
			gc.setFont(Font.font("Sans Serif", columnWidth*.12));
			gc.fillText(ScheduleDate.days[i], 2+i*columnWidth, columnWidth*.39);
			for(ScheduleEvent se : schedule.getEvents(i)) {
				double eheight = columnWidth*.18+(height - columnWidth*.19)*(double)(se.getDate().getRealTime())/86400.0;
				gc.fillText(se.getLocation(), 2+i*columnWidth, eheight);
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
