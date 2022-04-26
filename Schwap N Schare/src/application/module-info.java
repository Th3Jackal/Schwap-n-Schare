/*
 * @author Roshan Vareed
 * UTSA CS 3443 project
 * Spring 2022
 * 
 * module-info
 * 
 * JavaFX module info
 */
module Lab5 {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.base;
	
	opens application to javafx.graphics, javafx.fxml;
}
