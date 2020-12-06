package checkbox;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application{
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		HBox root = new HBox();
		root.setPadding(new Insets(20));
		
		String[] names = {"Up", "Down", "Stop"};
		CheckBox[] cb = new CheckBox[names.length];
		Label[] labels = new Label[names.length];
		
		VBox left = new VBox();
		HBox right = new HBox();
		left.setSpacing(10);
		right.setSpacing(10);
		right.setAlignment(Pos.CENTER);
		
		root.getChildren().addAll(left, right);
		
		for (int i = 0 ; i < cb.length; i++) {
			Label label = labels[i] = new Label(names[i]);
			label.setFont(new Font(40));
			
			CheckBox b = cb[i] = new CheckBox(names[i]);
			if (i == 0) {
				b.setSelected(true);
			}
			left.getChildren().add(b);
			
			b.setUserData(names[i]);
			b.selectedProperty().addListener(new ChangeListener<Boolean>() {
				@Override
				public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
					if (newValue == true) {
						label.setText(b.getUserData().toString());
					} else {
						label.setText(null);
					}
				}
			});
			right.getChildren().add(label);
			if (i != 0) {
				label.setText(null);
			}
		}
		
		Scene scene = new Scene(root, 400, 150);
		primaryStage.setTitle("Title");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);

	}

}
