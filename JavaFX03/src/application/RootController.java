package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class RootController implements Initializable{
	
	@FXML Button btn;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btn.setOnAction(event -> {
			if (btn.getText().equals("Action")) {
				btn.setText("액션");
			} else {
				btn.setText("Action");
			}
		});
		
	}
}
