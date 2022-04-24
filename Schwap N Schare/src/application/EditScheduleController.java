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
		schedule.addEvent("Test", 6, 0, 0, 0, "Square");
		schedule.addEvent("Group Project", 0, 5, 00, 00, "Mckinley Hall");
		schedule.addEvent("Counselor appointment", 5, 13, 15, 00, "NPB 1.28");
		
		//width 577
		//height 304
		//column width 82.4285714286
		//text height 13
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, 577.0, 304.0);
		gc.setFill(Color.BLACK);
		gc.setFont(Font.font("Sans Serif", 9.89142857143));
		gc.strokeLine(0,22.2557142857,577.0,22.2557142857);
		for(int i=0; i<7; i++) {
			gc.strokeLine(82.42857142857143*i,0, 82.42857142857143*i, 304.0);
			gc.setFont(Font.font("Sans Serif", 12.3642857143));
			gc.fillText(ScheduleDate.days[i], 2+i*82.42857142857143, 82.42857142857143*.19);
			gc.setFont(Font.font("Sans Serif", 9.89142857143));
			for(ScheduleEvent se : schedule.getEvents(i)) {
				double eheight = 82.42857142857143*.39+236.338571429*(double)(se.getDate().getRealTime())/86400.0;
				gc.fillText(se.getName(), 2+i*82.42857142857143, eheight);
				gc.fillText(se.getDate().getTime(), 2+i*82.42857142857143, eheight+13);
				gc.fillText(se.getLocation(), 2+i*82.42857142857143, eheight+26);
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
