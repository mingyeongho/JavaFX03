package mouseEventEx;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class RootController implements Initializable{

	@FXML Label lab;
	@FXML AnchorPane pane;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		pane.setOnMouseClicked(event-> {
			lab.setLayoutX(event.getX());
			lab.setLayoutY(event.getY());
		});
	}
}
