package slider;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		FlowPane root = new FlowPane();
		root.setPadding(new Insets(10));
		root.setVgap(10);
		
		Rectangle rect = new Rectangle(300, 100, Color.rgb(128,128,128));
		
		Label label = new Label("Red: 128, Green: 128, Blue: 128");
		
		Slider[] slider = new Slider[3];
		for (int i = 0; i < slider.length; i++) {
			slider[i] = new Slider(0, 255, 128);
			slider[i].setPrefWidth(300);
			slider[i].setShowTickLabels(true);
			slider[i].setShowTickMarks(true);
			slider[i].setMajorTickUnit(50);
			slider[i].setMinorTickCount(4);
			slider[i].valueProperty().addListener(new ChangeListener<Number>() {
				@Override
				public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
					int r = (int)slider[0].getValue();
					int g = (int)slider[1].getValue();
					int b = (int)slider[2].getValue();
					rect.setFill(Color.rgb(r,g,b));
					label.setText("Red: " + r + ", Green: " + g + ", Blue: " + b);
				}
			});
		}
		slider[0].setStyle("-fx-color: red");
		slider[1].setStyle("-fx-color: green");
		slider[2].setStyle("-fx-color: blue");
		root.getChildren().addAll(slider[0], slider[1], slider[2], rect, label);
		
		Scene scene = new Scene(root);
		primaryStage.setTitle("Title");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);

	}

}
