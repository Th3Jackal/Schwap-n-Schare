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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ViewScheduleController {
	
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
		Main.schedule1.readFromFile(Main.schedule1Name);
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
			for(ScheduleEvent se : Main.schedule1.getEvents(i)) {
				double eheight = 32.1471428571+243*(double)(se.getDate().getRealTime())/86400.0;
				String name=se.getName(),date=se.getDate().getTime(),location=se.getLocation();
				if(name.length() > 13)
					name=name.substring(0,11)+"...";
				if(location.length() > 13)
					location=location.substring(0,11)+"...";
				gc.fillText(name, 2+i*82.42857142857143, eheight);
				gc.fillText(date, 2+i*82.42857142857143, eheight+13);
				gc.fillText(location,2+i*82.42857142857143, eheight+26);
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
